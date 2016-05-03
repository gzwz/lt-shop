package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;


/**
 * 冻结金额
 * 
 * 
 */
@SuppressWarnings("serial")
public class MoneyFrozenCommand extends BaseCommand {

	/**
	 * 帐户id
	 */
	private String accountId;

	/**
	 * 冻结的金额
	 */
	private Double amount;

	/**
	 * 冻结说明
	 */
	private String remark;

	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 失效时间（解冻时间）
	 */
	private Date invalidDate;

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

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

}
