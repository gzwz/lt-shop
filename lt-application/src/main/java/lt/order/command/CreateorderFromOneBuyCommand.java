package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateorderFromOneBuyCommand extends BaseCommand {

	/**
	 * 收货地址ID
	 */
	private String addressId;

	/**
	 * 商品首图
	 */
	private String titleImage;

	/**
	 * 订单描述
	 */
	private String body;

	/**
	 * 购买用户id
	 */
	private String userId;
	
	/**
	 * 购买者账户
	 */
	private String buyerName;

	/**
	 * 购买数量
	 */
	private Integer num;

	/**
	 * 产品id
	 */
	private String productId;

	/**
	 * 支付总金额
	 */
	private String totalPrice;

	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 配送方式
	 */
	private String logistics;

	/**
	 * 商品备注
	 */
	private String remark;

	/**
	 * 商品展示地址
	 */
	private String showUrl;

	/**
	 * SKU商品id
	 */
	private String skuProductId;

	/**
	 * 是否用于预览确认（不保存）
	 */
	private boolean preview;

	/**
	 * 现金支付渠道
	 */
//	private String cashPayChannel;


	/**
	 * 订单名称(此处既是商品名称)
	 */
	private String subject;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}

	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	

}
