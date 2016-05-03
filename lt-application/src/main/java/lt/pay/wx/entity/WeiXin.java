package lt.pay.wx.entity;

import gzlazypack.common.component.StringIdBaseEntity;

@SuppressWarnings("serial")
public class WeiXin extends StringIdBaseEntity{
	
	/**
	 * 
		接口链接

		URL地址：https://api.mch.weixin.qq.com/pay/unifiedorder

	 */

	private String appid;			//	公众账号ID		必填
	
	private String mch_id;			//	商户号			必填
	
	private String nonce_str;		//	随机字符串  		必填
	
	private String sign;			//	签名  			必填
	
	private String body;			//	商品描述			必填
	
	private String out_trade_no;	//	商户订单号			必填
	
	private int total_fee;			//	总金额			必填
	
	private String spbill_create_ip;//	终端IP			必填		APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。

	private String notify_url;		//	通知地址			必填
	
	//		"JSAPI";//--公众号支付
	//		"NATIVE";//--原生扫码支付
	//		"APP";//--app支付
	private String trade_type;		//	交易类型			必填
	
	
	private String device_info;		//	设备号			否

	private String detail;			//	商品详情			否
	
	private String attach;			//	附加数据			否		附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	
	private String fee_type;		//	货币类型			否
	
	private String time_start;		//	交易起始时间		否		订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见
	
	private String time_expire;		//	交易结束时间		否		订单失效时间，
	
	private String goods_tag;		//	商品标记			否
	
	private String  product_id;		//	商品ID			否
	
	private String limit_pay;		//	指定支付方式		否
	
	private String openid;			//	用户标识			否

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
 
}
