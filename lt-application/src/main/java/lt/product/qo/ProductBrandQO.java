package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "productBrandService")
public class ProductBrandQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.LEFT_JOIN)
	private ProductCategoryQO productCategoryQO;
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private ProductBrandQO parentQO;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 * 
	 * 
	 */
	
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductBrand;
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductCategory;
	
	private String productCategoryIds;
	
	private String brandId;

	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;


	public ProductCategoryQO getProductCategoryQO() {
		return productCategoryQO;
	}

	public void setProductCategoryQO(ProductCategoryQO productCategoryQO) {
		this.productCategoryQO = productCategoryQO;
	}

	public Boolean getFetchProductCategory() {
		return fetchProductCategory;
	}

	public void setFetchProductCategory(Boolean fetchProductCategory) {
		this.fetchProductCategory = fetchProductCategory;
	}

	public String getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(String productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}


	public Boolean getFetchProductBrand() {
		return fetchProductBrand;
	}

	public void setFetchProductBrand(Boolean fetchProductBrand) {
		this.fetchProductBrand = fetchProductBrand;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public ProductBrandQO getParentQO() {
		return parentQO;
	}

	public void setParentQO(ProductBrandQO parentQO) {
		this.parentQO = parentQO;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	
}
