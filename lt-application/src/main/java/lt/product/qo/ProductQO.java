package lt.product.qo;

import lt.merchant.qo.MerchantQO;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@QueryConfig(daoBeanId = "productService")
public class ProductQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "productBrand", type = QueryConditionType.LEFT_JOIN)
	private ProductBrandQO productBrandQO;
	
	@QueryCondition(name = "productCategory", type = QueryConditionType.LEFT_JOIN)
	private ProductCategoryQO productCategoryQO;
	
	@QueryCondition(name = "merchants", type = QueryConditionType.LEFT_JOIN)
	private MerchantQO merchantQO;
	
	@QueryCondition(name = "screeningConditions", type = QueryConditionType.JOIN)
	private ScreeningConditionQO screeningConditionQO;
	
	@QueryCondition(name = "showInfo.name", ifTrueUseLike = "nameLike")
	private String name;
	
	private String productCatoryName;

	
	@QueryCondition(name = "status")
	private String status;
	
	private String brandId;
	
	private String page;
	
	private String productId;
	
	private String screeningConditionId;
	
	private String productCategoryId;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "productBrand", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductBrand;
	
	@QueryCondition(name = "screeningConditions", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchScreeningCondition;
	
	@QueryCondition(name = "productCategory", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductCategory;
	
	@QueryCondition(name = "merchant", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchMerchant;

	private Boolean nameLike;
    

	public ProductBrandQO getProductBrandQO() {
		return productBrandQO;
	}

	public void setProductBrandQO(ProductBrandQO productBrandQO) {
		this.productBrandQO = productBrandQO;
	}

	public ProductCategoryQO getProductCategoryQO() {
		return productCategoryQO;
	}

	public void setProductCategoryQO(ProductCategoryQO productCategoryQO) {
		this.productCategoryQO = productCategoryQO;
	}

	public MerchantQO getMerchantQO() {
		return merchantQO;
	}

	public void setMerchantQO(MerchantQO merchantQO) {
		this.merchantQO = merchantQO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getNameLike() {
		return nameLike;
	}

	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}

	public Boolean getFetchProductBrand() {
		return fetchProductBrand;
	}

	public void setFetchProductBrand(Boolean fetchProductBrand) {
		this.fetchProductBrand = fetchProductBrand;
	}

	public Boolean getFetchProductCategory() {
		return fetchProductCategory;
	}

	public void setFetchProductCategory(Boolean fetchProductCategory) {
		this.fetchProductCategory = fetchProductCategory;
	}

	public Boolean getFetchMerchant() {
		return fetchMerchant;
	}

	public void setFetchMerchant(Boolean fetchMerchant) {
		this.fetchMerchant = fetchMerchant;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getScreeningConditionId() {
		return screeningConditionId;
	}

	public void setScreeningConditionId(String screeningConditionId) {
		this.screeningConditionId = screeningConditionId;
	}

	public ScreeningConditionQO getScreeningConditionQO() {
		return screeningConditionQO;
	}

	public void setScreeningConditionQO(ScreeningConditionQO screeningConditionQO) {
		this.screeningConditionQO = screeningConditionQO;
	}

	public Boolean getFetchScreeningCondition() {
		return fetchScreeningCondition;
	}

	public void setFetchScreeningCondition(Boolean fetchScreeningCondition) {
		this.fetchScreeningCondition = fetchScreeningCondition;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCatoryName() {
		return productCatoryName;
	}

	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}
	
	
	
}
