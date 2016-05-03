package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 创建帐户变动明细
 * 
 *
 */
@SuppressWarnings("serial")
public class CreateVAChangeDetailCommand extends BaseCommand {

	private String virtualAccountId;

	/**
	 * 变动金额
	 */
	private Double amount;

	/**
	 * 帐户原有余额
	 */
	private Double oldBalance;

	/**
	 * 帐户新余额
	 */
	private Double newBanlance;

	/**
	 * 变动业务类型
	 */
	private String businessType;

	/**
	 * 变动类型
	 */
	private String type;

	/**
	 * 备注信息
	 */
	private String remark;

	public String getVirtualAccountId() {
		return virtualAccountId;
	}

	public void setVirtualAccountId(String virtualAccountId) {
		this.virtualAccountId = virtualAccountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(Double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public Double getNewBanlance() {
		return newBanlance;
	}

	public void setNewBanlance(Double newBanlance) {
		this.newBanlance = newBanlance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
