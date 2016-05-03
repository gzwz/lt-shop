package lt.virtualAccount.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.MoneyUtil;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.entity.DomainLink;
import lt.virtualAccount.command.CreateVirtualAccountCommand;
import lt.virtualAccount.exception.VirtualAccountException;

import org.hibernate.annotations.DynamicUpdate;


/**
 * 虚拟帐户
 * 
 * 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "VIRTUAL_ACCOUNT")
@SuppressWarnings("serial")
public class VirtualAccount extends StringIdBaseEntity {

	/**
	 * 帐户基本信息
	 */
	private VirtualAccountBaseInfo baseInfo;

	/**
	 * 帐户币种
	 */
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY_ID", nullable = false)
	private VACurrency currency;


	/**
	 * 帐户归属主体
	 */
	private DomainLink owner;

	/**
	 * 帐户余额
	 */
	private VirtualAccountBalance balance;

	/**
	 * 帐户状态
	 */
	private VirtualAccountStatus status;

	/**
	 * 帐户业务类型
	 */
	@Column(name = "BUSINESS_TYPE", length = 64)
	private String businessType;

	/**
	 * 创建
	 * 
	 * @param command
	 */
	public void create(CreateVirtualAccountCommand command,
			VACurrency VACurrency) {
		setId(UUIDGenerator.getUUID());

		setBaseInfo(new VirtualAccountBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setInvalidDate(command.getInvalidDate());
		getBaseInfo().setProxyAccount(VACurrency.getProxyCurrency());
		getBaseInfo().setCreateDate(new Date());

		setOwner(new DomainLink());
		getOwner().setDomainId(command.getOwnerId());
		getOwner().setDomainType(command.getOwnerType());
		getOwner().setDomainName(command.getOwnerName());

		setCurrency(VACurrency);

		setBalance(new VirtualAccountBalance());
		double totalAmount = command.getTotalAmount();
		getBalance().setTotalAmount(totalAmount);
		getBalance().setAvaiableAmount(totalAmount);
		getBalance().setFrozenAmount(0d);

		setBusinessType(command.getBusinessType());


		setStatus(new VirtualAccountStatus());
		getStatus().setEnable(true);
		getStatus().setClose(false);
	}

	/**
	 * 入帐
	 * 
	 * @param amount
	 * @throws VirtualAccountException
	 */
	public void moneyIn(Double amount) throws VirtualAccountException {
		checkEnable();

		Double totalAmount = MoneyUtil.add(getBalance().getTotalAmount(),
				amount);
		Double avaiableAmount = MoneyUtil.add(getBalance().getAvaiableAmount(),
				amount);
		getBalance().setAvaiableAmount(avaiableAmount);
		getBalance().setTotalAmount(totalAmount);
	}

	/**
	 * 重置积分为0
	 */
	public void reset() {
		getBalance().setAvaiableAmount(0D);
		getBalance().setTotalAmount(0D);
		getBalance().setFrozenAmount(0D);
	}

	/**
	 * 从可用余额出帐
	 * 
	 * @param amount
	 * @throws VirtualAccountException
	 */
	public void moneyOutFromAvaiable(Double amount)
			throws VirtualAccountException {
		checkEnable();

		if (getBalance().getAvaiableAmount() < amount) {
			throw new VirtualAccountException(
					VirtualAccountException.AVAIABLE_AMOUNT_NOT_ENOUTH,
					"可用余额不足");
		}

		Double totalAmount = MoneyUtil.sub(getBalance().getTotalAmount(),
				amount);
		Double avaiableAmount = MoneyUtil.sub(getBalance().getAvaiableAmount(),
				amount);
		getBalance().setAvaiableAmount(avaiableAmount);
		getBalance().setTotalAmount(totalAmount);
	}

	/**
	 * 从冻结金额出帐
	 * 
	 * @param amount
	 * @throws VirtualAccountException
	 */
	public void moneyOutFromFrozen(Double amount,
			VirtualAccount frozenVirtualAccount) throws VirtualAccountException {
		if (amount.doubleValue() != frozenVirtualAccount.getBalance()
				.getTotalAmount().doubleValue()) {
			throw new VirtualAccountException(
					VirtualAccountException.FROZEN_AMOUNT_WRONG, "冻结金额与出帐金额不等");
		}

		Double totalAmount = MoneyUtil.sub(getBalance().getTotalAmount(),
				amount);
		Double frozenAmount = MoneyUtil.sub(getBalance().getFrozenAmount(),
				amount);
		getBalance().setTotalAmount(totalAmount);
		getBalance().setFrozenAmount(frozenAmount);
	}

	/**
	 * 冻结余额
	 * 
	 * @param amount
	 * @throws VirtualAccountException
	 */
	public void moneyFrozen(Double amount) throws VirtualAccountException {
		checkEnable();

		if (getBalance().getAvaiableAmount().doubleValue() < amount) {
			throw new VirtualAccountException(
					VirtualAccountException.AVAIABLE_AMOUNT_NOT_ENOUTH,
					"可用余额不足");
		}

		Double frozenAmount = MoneyUtil.add(getBalance().getFrozenAmount(),
				amount);
		Double avaiableAmount = MoneyUtil.sub(getBalance().getAvaiableAmount(),
				amount);

		getBalance().setAvaiableAmount(avaiableAmount);
		getBalance().setFrozenAmount(frozenAmount);
	}

	/**
	 * 解冻余额
	 * 
	 * @param amount
	 * @throws VirtualAccountException
	 */
	public void moneyUnFreeze(Double amount) throws VirtualAccountException {
		checkEnable();

		if (getBalance().getTotalAmount().doubleValue() < amount) {
			throw new VirtualAccountException(
					VirtualAccountException.AVAIABLE_AMOUNT_NOT_ENOUTH,
					"可用余额不足");
		}

		Double frozenAmount = MoneyUtil.sub(getBalance().getFrozenAmount(),
				amount);
		Double avaiableAmount = MoneyUtil.add(getBalance().getAvaiableAmount(),
				amount);

		getBalance().setAvaiableAmount(avaiableAmount);
		getBalance().setFrozenAmount(frozenAmount);

	}

	/**
	 * 检查帐户正常状态
	 * 
	 * @throws VirtualAccountException
	 */
	public void checkEnable() throws VirtualAccountException {
		if (getStatus().getClose()) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_CLOSE, "帐户已关闭");
		}

		if (!getStatus().getEnable()) {
			throw new VirtualAccountException(
					VirtualAccountException.ACCOUNT_FORBIDDEN, "帐户禁用中");
		}
	}

	/**
	 * 启用
	 */
	public void enable() {
		getStatus().setEnable(true);
	}

	/**
	 * 禁用
	 */
	public void disable() {
		getStatus().setEnable(false);
	}

	/**
	 * 关闭
	 */
	public void close() {
		getStatus().setClose(true);
	}

	public VirtualAccountBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(VirtualAccountBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public VACurrency getCurrency() {
		return currency;
	}

	public void setCurrency(VACurrency currency) {
		this.currency = currency;
	}


	public VirtualAccountStatus getStatus() {
		return status;
	}

	public void setStatus(VirtualAccountStatus status) {
		this.status = status;
	}

	public VirtualAccountBalance getBalance() {
		return balance;
	}

	public void setBalance(VirtualAccountBalance balance) {
		this.balance = balance;
	}

	public DomainLink getOwner() {
		return owner;
	}

	public void setOwner(DomainLink owner) {
		this.owner = owner;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
