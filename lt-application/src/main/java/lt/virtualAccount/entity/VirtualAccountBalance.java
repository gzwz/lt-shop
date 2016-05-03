package lt.virtualAccount.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 帐户余额
 * 
 * @author yuxiaoxiang
 * 
 */
@Embeddable
public class VirtualAccountBalance {

	/**
	 * 总金额
	 */
	@Column(name = "TOTAL_AMOUNT", columnDefinition = M.MONEY_COLUMN)
	private Double totalAmount;

	/**
	 * 可用金额
	 */
	@Column(name = "AVAIABLE_AMOUNT", columnDefinition = M.MONEY_COLUMN)
	private Double avaiableAmount;

	/**
	 * 冻结金额
	 */
	@Column(name = "FROZEN_AMOUNT", columnDefinition = M.MONEY_COLUMN)
	private Double frozenAmount;
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getAvaiableAmount() {
		return avaiableAmount;
	}

	public void setAvaiableAmount(Double avaiableAmount) {
		this.avaiableAmount = avaiableAmount;
	}

	public Double getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(Double frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

}
