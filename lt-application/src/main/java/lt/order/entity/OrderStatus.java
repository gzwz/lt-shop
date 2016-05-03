package lt.order.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

@Embeddable
public class OrderStatus {
	/**
	 * 订单状态
	 */
	@Column(name = "status")
	private String currentValue;

	public final static String ORDER_STATUS_CREATE = "create"; // 已创建待付款
	public final static String ORDER_STATUS_CANCEL = "cancel"; // 取消
	public final static String ORDER_STATUS_PAID = "paid"; // 已付款等发货
	public final static String ORDER_STATUS_DELIVERY = "delivery"; // 已发货待收货
	public final static String ORDER_STATUS_FINISH = "finish"; // 已收货订单完成
	public final static String ORDER_STATUS_UNEVALUATE = "unevaluate";//待评价订单
	public final static String ORDER_STATUS_FINISHED = "finished";//订单完成 
	public final static String ORDER_STATUS_REMOVE = "remove"; // 已完成被删除

	/**
	 * 已支付，代表已支付后的全部状态
	 */
	@Type(type = "yes_no")
	@Column(name = "status_paid")
	private Boolean paid;

	/**
	 * 是否还能支付，已支付的为false，已取消的为false，其他为true
	 */
	@Type(type = "yes_no")
	@Column(name = "status_canPay")
	private Boolean canPay;


	/**
	 * 是否已关闭
	 */
	@Type(type = "yes_no")
	@Column(name = "close")
	private Boolean close;

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Boolean getCanPay() {
		return canPay;
	}

	public void setCanPay(Boolean canPay) {
		this.canPay = canPay;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

}
