package lt.virtualAccount.service;


import lt.virtualAccount.command.CloseVirtualAccountCommand;
import lt.virtualAccount.command.CreateVAChangeDetailCommand;
import lt.virtualAccount.command.CreateVirtualAccountCommand;
import lt.virtualAccount.command.DeleteVirtualAccountCommand;
import lt.virtualAccount.command.DisableVirtualAccountCommand;
import lt.virtualAccount.command.EnableVirtualAccountCommand;
import lt.virtualAccount.command.MoneyFrozenCommand;
import lt.virtualAccount.command.MoneyInCommand;
import lt.virtualAccount.command.MoneyOutCommand;
import lt.virtualAccount.command.MoneyTransferCommand;
import lt.virtualAccount.command.MoneyUnFreezeCommand;
import lt.virtualAccount.entity.VACurrency;
import lt.virtualAccount.entity.VirtualAccount;
import lt.virtualAccount.entity.VirtualAccountBalance;
import lt.virtualAccount.entity.VirtualAccountChangeDetail;
import lt.virtualAccount.exception.VirtualAccountException;
import lt.virtualAccount.qo.VirtualAccountQO;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.page.Pagination;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VirtualAccountService extends
		BaseDao<VirtualAccount, VirtualAccountQO> {

	@Autowired
	private VACurrencyService vaCurrencyService;
	@Autowired
	private VirtualAccountChangeDetailService virtualAccountChangeDetailService;

	/**
	 * 创建虚拟帐户
	 * 
	 * @param command
	 * @return
	 * @throws VirtualAccountException
	 */
	@Transactional(rollbackFor = VirtualAccountException.class)
	public VirtualAccount createVirtualAccount(
			CreateVirtualAccountCommand command) throws VirtualAccountException {
		VACurrency currency = vaCurrencyService.get(command.getVACurrencyId());

		if (currency == null) {
			throw new VirtualAccountException(
					VirtualAccountException.CURRENCY_NOT_EXIST, "虚拟货币"
							+ command.getVACurrencyId() + "不存在");
		}
		
		VirtualAccount virtualAccount = new VirtualAccount();
		virtualAccount.create(command, currency);

		save(virtualAccount);

		return virtualAccount;
	}

	/**
	 * 启用虚拟帐户
	 * 
	 * @param command
	 */
	public void enableVirtualAccount(EnableVirtualAccountCommand command) {

		VirtualAccount virtualAccount = get(command.getAccountId());
		virtualAccount.enable();
		update(virtualAccount);
	}

	/**
	 * 禁用虚拟帐户
	 * 
	 * @param command
	 */
	public void disableVirtualAccount(DisableVirtualAccountCommand command) {

		VirtualAccount virtualAccount = get(command.getAccountId());
		virtualAccount.disable();
		update(virtualAccount);
	}

	/**
	 * 关闭虚拟帐户，不会再启用
	 * 
	 * @param command
	 */
	public void closeVirtualAccount(CloseVirtualAccountCommand command) {

		VirtualAccount virtualAccount = get(command.getAccountId());
		virtualAccount.close();
		update(virtualAccount);
	}

	/**
	 * 删除虚拟帐户，有余额和记录时不可删
	 * 
	 * @param command
	 * @throws VirtualAccountException
	 */
	public void deleteVirtualAccount(DeleteVirtualAccountCommand command)
			throws VirtualAccountException {
		VirtualAccount virtualAccount = get(command.getAccountId());

		if (virtualAccount.getBalance().getTotalAmount() >= 0D) {
			throw new VirtualAccountException(
					VirtualAccountException.AMOUNT_NOT_NULL, "帐户还有余额不可删除");
		} else {
			delete(virtualAccount);
		}

		delete(virtualAccount);
	}

	/**
	 * 入帐
	 * 
	 * @param command
	 * @return
	 * @throws VirtualAccountException
	 */
	public VirtualAccountChangeDetail moneyIn(MoneyInCommand command)
			throws VirtualAccountException {
		VirtualAccount virtualAccount = get(command.getAccountId(), true);

		if (virtualAccount == null) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_NOT_EXIST, "帐户不存在");
		}

		if (!virtualAccount.getStatus().getEnable()) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_FORBIDDEN, "帐户禁用中");
		}

		if (virtualAccount.getStatus().getClose()) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_CLOSE, "帐户已关闭");
		}

		// 创建变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand.setAmount(command.getAmount());
		createVAChangeDetailCommand.setOldBalance(virtualAccount.getBalance()
				.getAvaiableAmount());
		createVAChangeDetailCommand.setRemark(command.getRemark());
		createVAChangeDetailCommand.setBusinessType(command.getBusinessType());
		createVAChangeDetailCommand.setType("money_in");

		virtualAccount.moneyIn(command.getAmount());
		update(virtualAccount);

		createVAChangeDetailCommand.setNewBanlance(virtualAccount.getBalance()
				.getAvaiableAmount());
		VirtualAccountChangeDetail changeDetail = virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand);

		return changeDetail;
	}

	/**
	 * 出帐
	 * 
	 * @param command
	 * @return
	 * @throws VirtualAccountException
	 */
	public VirtualAccountChangeDetail moneyOut(MoneyOutCommand command)
			throws VirtualAccountException {
		VirtualAccount virtualAccount = get(command.getAccountId());
		if (virtualAccount == null) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_NOT_EXIST, "帐户不存在");
		}

		// 创建变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand.setAmount(command.getAmount());
		createVAChangeDetailCommand.setOldBalance(virtualAccount.getBalance()
				.getTotalAmount());
		createVAChangeDetailCommand.setRemark(command.getRemark());
		createVAChangeDetailCommand.setBusinessType(command.getBusinessType());
		createVAChangeDetailCommand.setType("money_out");

		if (command.getFrozenId() == null) {
			// 从可用余额付款
			virtualAccount.moneyOutFromAvaiable(command.getAmount());
		} else {
			// 从冻结金额付款
			VirtualAccount frozenVirtualAccount = get(command.getFrozenId());
			virtualAccount.moneyOutFromFrozen(command.getAmount(),
					frozenVirtualAccount);
			frozenVirtualAccount.close();
		}

		createVAChangeDetailCommand.setNewBanlance(virtualAccount.getBalance()
				.getTotalAmount());
		VirtualAccountChangeDetail changeDetail = virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand);

		return changeDetail;
	}

	/**
	 * 转帐
	 * 
	 * @param command
	 * @return
	 * @throws VirtualAccountException
	 */
	@Transactional(rollbackFor = VirtualAccountException.class)
	public VirtualAccountChangeDetail[] moneyTransfer(
			MoneyTransferCommand command) throws VirtualAccountException {
		VirtualAccount inAccount = get(command.getInAccountId());
		VirtualAccount outAccount = get(command.getOutAccountId());

		// 创建入帐变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand1 = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand1.setAmount(command.getAmount());
		createVAChangeDetailCommand1.setOldBalance(inAccount.getBalance()
				.getAvaiableAmount());
		createVAChangeDetailCommand1.setRemark(command.getInRemark());
		createVAChangeDetailCommand1.setBusinessType(command
				.getInBusinessType());
		createVAChangeDetailCommand1.setType("money_in");

		// 创建出帐变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand2 = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand2.setAmount(command.getAmount());
		createVAChangeDetailCommand2.setOldBalance(outAccount.getBalance()
				.getAvaiableAmount());
		createVAChangeDetailCommand2.setRemark(command.getOutRemark());
		createVAChangeDetailCommand2.setBusinessType(command
				.getOutBusinessType());
		createVAChangeDetailCommand2.setType("money_out");

		inAccount.moneyIn(command.getAmount());
		if (command.getFrozenId() == null) {
			// 从可用余额付款
			outAccount.moneyOutFromAvaiable(command.getAmount());
		} else {
			// 从冻结金额付款
			VirtualAccount frozenVirtualAccount = get(command.getFrozenId());
			outAccount.moneyOutFromFrozen(command.getAmount(),
					frozenVirtualAccount);
		}

		createVAChangeDetailCommand1.setNewBanlance(inAccount.getBalance()
				.getAvaiableAmount());
		createVAChangeDetailCommand2.setNewBanlance(outAccount.getBalance()
				.getAvaiableAmount());
		VirtualAccountChangeDetail changeDetail1 = virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand1);
		VirtualAccountChangeDetail changeDetail2 = virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand1);

		VirtualAccountChangeDetail[] virtualAccountChangeDetails = new VirtualAccountChangeDetail[2];
		virtualAccountChangeDetails[0] = changeDetail1;
		virtualAccountChangeDetails[1] = changeDetail2;
		return virtualAccountChangeDetails;
	}

	/**
	 * 冻结金额
	 * 
	 * @param command
	 * @return
	 * @throws VirtualAccountException
	 */
	public Object[] moneyFrozen(MoneyFrozenCommand command)
			throws VirtualAccountException {
		VirtualAccount virtualAccount = get(command.getAccountId());

		// 创建存冻结余额的冻结帐户
		CreateVirtualAccountCommand createCommand = new CreateVirtualAccountCommand();
		createCommand.setVACurrencyId(virtualAccount.getCurrency().getId());
		createCommand.setParentId(virtualAccount.getId());
		createCommand.setOwnerId(virtualAccount.getOwner().getDomainId());
		createCommand.setOwnerType(virtualAccount.getOwner().getDomainType());
		createCommand.setName(virtualAccount.getBaseInfo().getName() + command.getBusinessType() + "冻结帐户");
		createCommand.setOwnerName(virtualAccount.getOwner().getDomainName());
		createCommand.setInvalidDate(command.getInvalidDate());
		createCommand.setBusinessType(command.getBusinessType());
		
		// 创建变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand.setAmount(command.getAmount());
		createVAChangeDetailCommand.setOldBalance(virtualAccount.getBalance()
				.getTotalAmount());
		createVAChangeDetailCommand.setRemark(command.getRemark());
		createVAChangeDetailCommand.setBusinessType(command.getBusinessType());
		createVAChangeDetailCommand.setType("money_frozen");

		VirtualAccount frozenVirtualAccount = createVirtualAccount(createCommand);
		frozenVirtualAccount.moneyIn(command.getAmount());
		frozenVirtualAccount.moneyFrozen(command.getAmount());

		virtualAccount.moneyFrozen(command.getAmount());
		
		save(frozenVirtualAccount);
		update(virtualAccount);

		createVAChangeDetailCommand.setNewBanlance(virtualAccount.getBalance()
				.getTotalAmount());
		virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand);

		Object[] results = new Object[2];
		results[0] = virtualAccount.getBalance();
		results[1] = frozenVirtualAccount.getId();
		return results;
	}

	/**
	 * 解冻金额
	 * 
	 * @param command
	 * @return
	 * @throws BaseException
	 */
	public VirtualAccountBalance moneyUnFreeze(MoneyUnFreezeCommand command)
			throws VirtualAccountException {

		if (command.getFrozenId() == null) {
			throw new VirtualAccountException(
					VirtualAccountException.NECESSARY_PARAM_NULL, "冻结id不能为空");
		}

		VirtualAccount frozenVirtualAccount = get(command.getFrozenId());
		if (frozenVirtualAccount == null) {
			throw new VirtualAccountException(
					VirtualAccountException.FROZEN_ACCOUNT_NOT_EXIST, "冻结金额不存在");
		}
		
		VirtualAccount virtualAccount = get(command.getAccountId());
		
		// 创建变动明细
		CreateVAChangeDetailCommand createVAChangeDetailCommand = new CreateVAChangeDetailCommand();
		createVAChangeDetailCommand.setAmount(command.getAmount());
		createVAChangeDetailCommand.setOldBalance(virtualAccount.getBalance()
				.getTotalAmount());
		createVAChangeDetailCommand.setRemark(command.getRemark());
		createVAChangeDetailCommand.setBusinessType(command.getBusinessType());
		createVAChangeDetailCommand.setType("money_un_frozen");
		
		virtualAccount.moneyUnFreeze(command.getAmount());

		// 关闭冻结帐户，余额不改，保留历史状态
		frozenVirtualAccount.close();

		update(virtualAccount);
		update(frozenVirtualAccount);
		
		createVAChangeDetailCommand.setNewBanlance(virtualAccount.getBalance()
				.getTotalAmount());
		virtualAccountChangeDetailService
				.createVAChangeDatail(createVAChangeDetailCommand);
		
		return virtualAccount.getBalance();
	}
	
	public Pagination query(VirtualAccountQO qo){
		Pagination pagination = new Pagination();
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = queryPagination(pagination);
			return pagination;
	}

	@Override
	public VirtualAccount queryUnique(VirtualAccountQO qo) {
		return super.queryUnique(qo);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, VirtualAccountQO qo) {
		return criteria;
	}

	@Override
	protected Class<VirtualAccount> getEntityClass() {
		return VirtualAccount.class;
	}
}
