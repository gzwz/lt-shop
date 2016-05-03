package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 出帐
 * 
 * 
 */
@SuppressWarnings("serial")
public class MoneyOutCommand extends BaseCommand {

	/**
	 * 帐户id
	 */
	private String accountId;

	/**
	 * 冻结id 有值时使用冻结的金额做扣除，金额要和冻结的对应金额相等 为空时使用可用余额扣除
	 */
	private String frozenId;

	/**
	 * 出帐的金额
	 */
	private Double amount;

	/**
	 * 出帐说明
	 */
	private String remark;

	/**
	 * 业务类型
	 */
	private String businessType;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFrozenId() {
		return frozenId;
	}

	public void setFrozenId(String frozenId) {
		this.frozenId = frozenId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
