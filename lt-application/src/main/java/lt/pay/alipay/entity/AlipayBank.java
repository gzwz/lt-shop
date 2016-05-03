package lt.pay.alipay.entity;

import gzlazypack.common.component.StringIdBaseEntity;

@SuppressWarnings("serial")
public class AlipayBank extends StringIdBaseEntity {
	
	/** 商户网站订单系统中唯一订单号，必填--必传 */
	private String WIDout_trade_no;
	/** 订单名称--必传 */
	private String WIDsubject;
	/** 付款金额--必传 */
	private Double WIDtotal_fee;
	/** 订单描述 */
	private String WIDbody;
	/**默认网银：必填，银行简码请参考接口技术文档 */
	private String WIDdefaultbank;
	/** 商品展示地址 */
	private String WIDshow_url;

	public AlipayBank(){}

	public AlipayBank(String wIDout_trade_no, String wIDsubject,
			Double wIDtotal_fee, String wIDbody, String wIDdefaultbank,
			String wIDshow_url) {
		super();
		WIDout_trade_no = wIDout_trade_no;
		WIDsubject = wIDsubject;
		WIDtotal_fee = wIDtotal_fee;
		WIDbody = wIDbody;
		WIDdefaultbank = wIDdefaultbank;
		WIDshow_url = wIDshow_url;
	}

	public String getWIDout_trade_no() {
		return WIDout_trade_no;
	}

	public void setWIDout_trade_no(String wIDout_trade_no) {
		WIDout_trade_no = wIDout_trade_no;
	}

	public String getWIDsubject() {
		return WIDsubject;
	}

	public void setWIDsubject(String wIDsubject) {
		WIDsubject = wIDsubject;
	}

	public Double getWIDtotal_fee() {
		return WIDtotal_fee;
	}

	public void setWIDtotal_fee(Double wIDtotal_fee) {
		WIDtotal_fee = wIDtotal_fee;
	}

	public String getWIDbody() {
		return WIDbody;
	}

	public void setWIDbody(String wIDbody) {
		WIDbody = wIDbody;
	}

	public String getWIDdefaultbank() {
		return WIDdefaultbank;
	}

	public void setWIDdefaultbank(String wIDdefaultbank) {
		WIDdefaultbank = wIDdefaultbank;
	}

	public String getWIDshow_url() {
		return WIDshow_url;
	}

	public void setWIDshow_url(String wIDshow_url) {
		WIDshow_url = wIDshow_url;
	}
	
	
	 
}
