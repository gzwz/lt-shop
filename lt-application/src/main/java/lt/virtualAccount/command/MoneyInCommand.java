package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 入帐
 * 
 * 
 */
@SuppressWarnings("serial")
public class MoneyInCommand extends BaseCommand {

	/**
	 * 帐户id
	 */
	private String accountId;

	/**
	 * 入帐的金额
	 */
	private Double amount;

	/**
	 * 入帐说明
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
