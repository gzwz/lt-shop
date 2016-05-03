package lt.pay.alipay.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.DateUtil;
import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 
 * @author wz
 * 参考地址
 * https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.S7Bh2b&treeId=62&articleId=103743&docType=1
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "NotifyData")
public class NotifyData extends StringIdBaseEntity{
	
	/**
	 *	 通知时间  格式为yyyy-MM-dd HH:mm:ss。
	 */
	private Date notify_time;
	
	/**
	 *	通知类型
	 */
	private String notify_type;
	
	/**
	 * 	通知校验ID
	 */
	private String notify_id;

	/**
	 *	 签名方式 DSA、RSA、MD5三个值可选 必须大写。
	 */
	private String sign_type;
	
	/**
	 * 	签名
	 */
	private String sign;
	
	/**
	 *	商户网站唯一订单号
	 */
	@Column(length = 64)
	private String out_trade_no;
	
	/**
	 * 商品名称
	 */
	@Column(length = 256)
	private String subject;
	
	/**
	 * 支付类型
	 * 枚举名称
	 * 1     商品购买
	 * 4     捐赠
	 * 47  电子卡券
	 */
	@Column(length = 4)
	private String payment_type;
	
	/**
	 * 支付宝交易号
	 * 
	 */
	@Column(length = 64)
	private String trade_no;
	
	/**
	 * 交易状态
	 * 枚举名称
	 * WAIT_BUYER_PAY  交易创建，等待买家付款。
	 * TRADE_CLOSED	      在指定时间段内未支付时关闭的交易； 在交易完成全额退款成功时关闭的交易。
	 * TRADE_SUCCESS   交易成功，且可对该交易做操作，如：多级分润、退款等。
	 * TRADE_PENDING   等待卖家收款（买家付款后，如果卖家账号被冻结）。
	 * TRADE_FINISHED  交易成功且结束，即不可再做任何操作。
	 * 
	 */
	@Column(length = 20)
	private String trade_status;
	
	/**
	 * 交易创建时间
	 */
	private Date gmt_create;
	
	/**
	 * 交易付款时间
	 */
	private Date gmt_payment;
	
	/**
	 * 交易关闭时间 格式为yyyy-MM-dd HH:mm:ss。
	 */
	private Date gmt_close;
	
	/**
	 *	 退款状态
	 *	枚举名称
	 *	REFUND_SUCCESS
	 *	REFUND_CLOSED
	 */
	@Column(length = 30)
	private String refund_status;
	
	/**
	 *	 退款时间 格式为yyyy-MM-dd HH:mm:ss。
	 */
	private Date gmt_refund;
	
	/**
	 *	 卖家支付宝账号
	 */
	@Column(length = 50)
	private String seller_email;
	
	/**
	 * 买家支付宝账号
	 */
	@Column(length = 50)
	private String buyer_email;
	
	/**
	 * 卖家支付宝账户号
	 */
	@Column(length = 30)
	private String seller_id;
	
	/**
	 * 买家支付宝账户号
	 */
	@Column(length = 30)
	private String buyer_id;
	
	/**
	 * 商品单价
	 */
	private Double price;
	
	/**
	 * 交易金额
	 */
	private Double total_fee;
	
	/**
	 * 购买数量
	 */
	private Integer quantity;
	
	/**
	 * 商品描述
	 */
	@Column(length = 400)
	private String body;
	
	/**
	 * 折扣
	 */
	private Double discount;
	
	/**
	 * 是否调整总价
	 */
	@Column(length = 1)
	private String is_total_fee_adjust;
	
	/**
	 * 是否使用红包买家
	 */
	@Column(length = 1)
	private String use_coupon;
	
	/**
	 * 公用回传参数
	 */
	private String extra_common_param;
	
	/**
	 * 是否扫码支付
	 */
	private String business_scene;
	 ///////////////////////////////////////
	
	public void create(Map<String, String> params) {
		setId(params.get("out_trade_no"));
		
		setBuyer_id(params.get("buyer_id"));
		setTrade_no(params.get("trade_no"));
		setBody(params.get("body"));
		setUse_coupon(params.get("use_coupon"));
		setNotify_time(DateUtil.parseDateTime(params.get("notify_time")));
		setSubject(params.get("subject"));
		setSign_type(params.get("sign_type"));
		setIs_total_fee_adjust(params.get("is_total_fee_adjust"));
		setNotify_type(params.get("notify_type"));
		setOut_trade_no(params.get("out_trade_no"));
		setGmt_payment(DateUtil.parseDateTime(params.get("gmt_payment")));
		setTrade_status(params.get("trade_status"));
		setDiscount(Double.valueOf(params.get("discount")));
		setSign(params.get("sign"));
		setBuyer_email(params.get("buyer_email"));
		setGmt_create(DateUtil.parseDateTime(params.get("gmt_create")));
		setPrice(Double.valueOf(params.get("price")));
		setTotal_fee(Double.valueOf(params.get("total_fee")));
		setQuantity(Integer.parseInt(params.get("quantity")));
		setSeller_id(params.get("seller_id"));
		setNotify_id(params.get("notify_id"));
		setSeller_email(params.get("seller_email"));
		setPayment_type(params.get("payment_type"));
	}
	
	
	public Date getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public Date getGmt_close() {
		return gmt_close;
	}

	public void setGmt_close(Date gmt_close) {
		this.gmt_close = gmt_close;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}


	public Date getGmt_refund() {
		return gmt_refund;
	}


	public void setGmt_refund(Date gmt_refund) {
		this.gmt_refund = gmt_refund;
	}


	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getUse_coupon() {
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getBusiness_scene() {
		return business_scene;
	}

	public void setBusiness_scene(String business_scene) {
		this.business_scene = business_scene;
	}


	
}
