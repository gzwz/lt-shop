package lt.order.command;

import java.util.Date;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateOrderConsolidationCommand extends BaseCommand{

	/**
	 * 合并订单总价
	 */
	private Double totalPrice;
	

	/**
	 * 支付时间
	 */
	private Date payDate;
	
	/**
	 * 合并后的订单昵称
	 */
	private String name;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
