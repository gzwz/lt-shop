package lt.pay.alipay.entity;

import gzlazypack.common.component.StringIdBaseEntity;

@SuppressWarnings("serial")
public class Alipay extends StringIdBaseEntity {
	
	/** 商城订单号--必传 */
	private String outTradeNo;
	/** 订单名称--必传 */
	private String subject;
	/** 付款金额--必传 */
	private Double totalFee;
	/** 订单描述 */
	private String body;
	/** 商品展示地址 */
	private String showUrl;

	public Alipay(){}
	
	public Alipay(String outTradeNo, String subject, Double totalFee,
			String body, String showUrl) {
		super();
		this.outTradeNo = outTradeNo;
		this.subject = subject;
		this.totalFee = totalFee;
		this.body = body;
		this.showUrl = showUrl;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}
}
