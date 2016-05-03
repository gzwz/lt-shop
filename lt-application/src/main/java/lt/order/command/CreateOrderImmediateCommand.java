package lt.order.command;


import gzlazypack.common.component.BaseCommand;


/**
 * 直接购买预览订单
 * 
 * @author outman
 *
 */
@SuppressWarnings("serial")
public class CreateOrderImmediateCommand extends BaseCommand {

	/**
	 * 购买用户id
	 */
	private String userId;

	/**
	 * SKU商品id
	 */
	private String skuProductId;

	/**
	 * 购买数量
	 */
	private Integer num;
	
	private String skuSpecInfo;

	/**
	 * 是否用于预览确认（不保存）
	 */
	private boolean preview;


	/**
	 * 产品id
	 */
	private String productId;

	/**
	 * 收货地址ID
	 */
	private String addressId;

	/**
	 * 现金支付渠道
	 */
	private String cashPayChannel;

	
    private String remark;
	
	private  String   pickUp;
	
	private String merchantId;

	public String getCashPayChannel() {
		return cashPayChannel;
	}

	public void setCashPayChannel(String cashPayChannel) {
		this.cashPayChannel = cashPayChannel;
	}
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	

}
