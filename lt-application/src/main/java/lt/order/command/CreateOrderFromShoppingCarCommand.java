package lt.order.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;


/**
 * 从购物车预览订单
 * 
 * @author outman
 *
 */
@SuppressWarnings("serial")
public class CreateOrderFromShoppingCarCommand extends BaseCommand {

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 是否用于预览确认（不保存）
	 */
	private boolean preview;

	/**
	 * 收件人地址信息
	 */
	private String addressId;

	/**
	 * 购物车项ID字符串，逗号分隔
	 */
	private String shoppingCarItemId;

	/**
	 * 收货地址ID
	 */
	private String userAddressId;

	/**
	 * 现金支付渠道
	 */
	private String cashPayChannel;
	
	private String remark;
		
	private  String   pickUp;
	
	private String merchantId;
	
	private Double totalePrice;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getCashPayChannel() {
		return cashPayChannel;
	}

	public void setCashPayChannel(String cashPayChannel) {
		this.cashPayChannel = cashPayChannel;
	}

	public String getShoppingCarItemId() {
		return shoppingCarItemId;
	}

	public void setShoppingCarItemId(String shoppingCarItemId) {
		this.shoppingCarItemId = shoppingCarItemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
	}


	public String getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public Double getTotalePrice() {
		return totalePrice;
	}

	public void setTotalePrice(Double totalePrice) {
		this.totalePrice = totalePrice;
	}
	

}
