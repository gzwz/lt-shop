package lt.product.command;

import java.util.List;


import lt.base.command.CreateImageCommand;
import gzlazypack.common.component.BaseCommand;

public class CreateProductCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	private String productCategoryId;
	
	private String productBrandId;
	
	private String merchantId;
	
	private List<String> merchants;
	
	
	private String images;
	
	private List<String> screeningIds;
	
	private String screeningId;
	
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品首图
	 */
	private String titleImage;


	/**
	 * 商品简介
	 */
	private String intro;

	/**
	 * 最低价
	 */
	private Double lowestPrice;

	/**
	 * 市价
	 */
	private Double originalPrice;
	
	/**
	 * 创建多张图片
	 */
	private List<CreateImageCommand> createImageCommandList;
	
	
	/**
	 * 数量
	 */
	private Integer number;
	
	/**
	 * 售价
	 */
	private Double price;
   
	/**
	 * 格式：白色车型尺寸
	 */
	private String skuSpecInfo;
	
	
	private String parameterId;
	
	
	private String productId;
	
	
	private String proportyName;
	
	
	private String proportyValueItem;
	
	private String proportyValueId;
	
	/**
	 * 移动端商品详情
	 */
	private String mobileDetail;

	/**
	 * PC端商品详情
	 */
	private String pcDetail;
	
	

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductBrandId() {
		return productBrandId;
	}

	public void setProductBrandId(String productBrandId) {
		this.productBrandId = productBrandId;
	}

	public List<String> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<String> merchants) {
		this.merchants = merchants;
	}

	public List<CreateImageCommand> getCreateImageCommandList() {
		return createImageCommandList;
	}

	public void setCreateImageCommandList(
			List<CreateImageCommand> createImageCommandList) {
		this.createImageCommandList = createImageCommandList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProportyName() {
		return proportyName;
	}

	public void setProportyName(String proportyName) {
		this.proportyName = proportyName;
	}

	public String getProportyValueItem() {
		return proportyValueItem;
	}

	public void setProportyValueItem(String proportyValueItem) {
		this.proportyValueItem = proportyValueItem;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getProportyValueId() {
		return proportyValueId;
	}

	public void setProportyValueId(String proportyValueId) {
		this.proportyValueId = proportyValueId;
	}

	public String getMobileDetail() {
		return mobileDetail;
	}

	public void setMobileDetail(String mobileDetail) {
		this.mobileDetail = mobileDetail;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public List<String> getScreeningIds() {
		return screeningIds;
	}

	public void setScreeningIds(List<String> screeningIds) {
		this.screeningIds = screeningIds;
	}

	public String getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(String screeningId) {
		this.screeningId = screeningId;
	}

}
