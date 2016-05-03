package lt.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderBaseInfo {

	/**
	 * 订单总价
	 */
	@Column(name = "total_price", columnDefinition = M.MONEY_COLUMN)
	private Double totalPrice;

	/**
	 * 现金支付金额
	 */
	@Column(name = "cash_pay_price", columnDefinition = M.MONEY_COLUMN)
	private Double cashPayPrice;
	
	/**
	 * 第三方支付机构生成的现金支付交易号
	 */
	@Column(name = "payment_out_id")
	private String paymentOutId;

	/**
	 * 现金支付渠道
	 */
	@Column(name = "cash_pay_channel")
	private String cashPayChannel;

	public final static String PAY_CHANNEL_YL = "china_union_pay"; // 银联
	public final static String PAY_CHANNEL_ALIPAY = "alipay"; // 支付宝
	public final static String PAY_CHANNEL_WX = "wechat"; // 微信

	/**
	 * 创建时间
	 * 
	 */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * 支付时间
	 */
	@Column(name = "pay_date")
	private Date payDate;
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getCashPayPrice() {
		return cashPayPrice;
	}

	public void setCashPayPrice(Double cashPayPrice) {
		this.cashPayPrice = cashPayPrice;
	}

	public String getCashPayChannel() {
		return cashPayChannel;
	}

	public void setCashPayChannel(String cashPayChannel) {
		this.cashPayChannel = cashPayChannel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPaymentOutId() {
		return paymentOutId;
	}

	public void setPaymentOutId(String paymentOutId) {
		this.paymentOutId = paymentOutId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	

}
