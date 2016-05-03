package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 解冻金额
 * 
 * 
 */
@SuppressWarnings("serial")
public class MoneyUnFreezeCommand extends BaseCommand {

	/**
	 * 帐户id
	 */
	private String accountId;

	/**
	 * 冻结id，冻结操作时生成返回
	 */
	private String frozenId;

	/**
	 * 解冻的金额，必须与冻结时的金额相等
	 */
	private Double amount;

	/**
	 * 解冻说明
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
