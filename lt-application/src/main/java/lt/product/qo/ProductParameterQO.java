package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@QueryConfig(daoBeanId = "productParameterService")
public class ProductParameterQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.LEFT_JOIN)
	private ProductCategoryQO productCategoryQO;
	
	@QueryCondition(name = "productParameterValues", type = QueryConditionType.LEFT_JOIN)
	private ProductParameterValueQO productParameterValueQO;
	
	
	
	private String productCategoryIds;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "productParameterValues", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductParameterValue;
	
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductCategory;

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

	public ProductParameterValueQO getProductParameterValueQO() {
		return productParameterValueQO;
	}

	public void setProductParameterValueQO(
			ProductParameterValueQO productParameterValueQO) {
		this.productParameterValueQO = productParameterValueQO;
	}

	public Boolean getFetchProductParameterValue() {
		return fetchProductParameterValue;
	}

	public void setFetchProductParameterValue(Boolean fetchProductParameterValue) {
		this.fetchProductParameterValue = fetchProductParameterValue;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
}
