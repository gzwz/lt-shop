package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyShoppingCarItemCommand extends BaseCommand{
	
	private String shoppingCarItemId;
	/**
	 * 所属用户
	 */
	private String userId;
	
	/**
	 * sku商品id，SKUCommonProduct的id
	 */
	private String skuProductId;

	/**
	 * 规格颜色，如“灰色，车型”
	 */
	private String skuSpecInfo;

	/**
	 * 商品名称
	 */
	private String productName;
	
	private String productCatoryName;

	/**
	 * 商品图片链接
	 */
	private String titleImageUrl;

	/**
	 * 所选数量
	 */
	private Integer num;

	/**
	 * 商品原价，未计算任何折扣和优惠的商品售价 取值SKUCommonProduct.price
	 */
	private Double originalPrice;


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

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitleImageUrl() {
		return titleImageUrl;
	}

	public void setTitleImageUrl(String titleImageUrl) {
		this.titleImageUrl = titleImageUrl;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getProductCatoryName() {
		return productCatoryName;
	}

	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}

	public String getShoppingCarItemId() {
		return shoppingCarItemId;
	}

	public void setShoppingCarItemId(String shoppingCarItemId) {
		this.shoppingCarItemId = shoppingCarItemId;
	}
	
}
