package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 转帐
 * 
 * 
 */
@SuppressWarnings("serial")
public class MoneyTransferCommand extends BaseCommand {

	/**
	 * 入帐帐户id
	 */
	private String inAccountId;

	/**
	 * 出帐帐户id
	 */
	private String outAccountId;

	/**
	 * 冻结id 有值时出帐方使用冻结的金额做扣除，金额要和冻结的对应金额相等 为空时使用出帐方可用余额扣除
	 */
	private String frozenId;

	/**
	 * 转帐金额
	 */
	private Double amount;

	/**
	 * 入帐说明
	 */
	private String inRemark;

	/**
	 * 入帐业务类型
	 */
	private String inBusinessType;

	/**
	 * 出帐说明
	 */
	private String outRemark;

	/**
	 * 出帐业务类型
	 */
	private String outBusinessType;

	public String getInAccountId() {
		return inAccountId;
	}

	public void setInAccountId(String inAccountId) {
		this.inAccountId = inAccountId;
	}

	public String getOutAccountId() {
		return outAccountId;
	}

	public void setOutAccountId(String outAccountId) {
		this.outAccountId = outAccountId;
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

	public String getInRemark() {
		return inRemark;
	}

	public void setInRemark(String inRemark) {
		this.inRemark = inRemark;
	}

	public String getInBusinessType() {
		return inBusinessType;
	}

	public void setInBusinessType(String inBusinessType) {
		this.inBusinessType = inBusinessType;
	}

	public String getOutRemark() {
		return outRemark;
	}

	public void setOutRemark(String outRemark) {
		this.outRemark = outRemark;
	}

	public String getOutBusinessType() {
		return outBusinessType;
	}

	public void setOutBusinessType(String outBusinessType) {
		this.outBusinessType = outBusinessType;
	}

}
