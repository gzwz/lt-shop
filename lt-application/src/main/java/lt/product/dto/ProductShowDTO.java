package lt.product.dto;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ProductShowDTO extends BaseDTO{

	private String merchantName;
	
	private String brandName;
	
	private String productName;
	
	private String skuSpecInfo;
	
	private Double skuPrice;
	
	private String urlImage;
	
	private String productCatoryName;
	
	private String productId;
	
	private int num;
	
	private String merchantId;
	

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getProductCatoryName() {
		return productCatoryName;
	}

	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	

}
