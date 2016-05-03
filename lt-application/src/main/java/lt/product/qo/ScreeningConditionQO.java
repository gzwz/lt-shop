package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "screeningConditionService")
public class ScreeningConditionQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private ScreeningConditionQO parendQO;
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.LEFT_JOIN)
	private ProductCategoryQO productCategoryQO;
	
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	
	@QueryCondition(name = "productCategorys", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductCategory;
	
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchScreeningCondition;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public ScreeningConditionQO getParendQO() {
		return parendQO;
	}

	public void setParendQO(ScreeningConditionQO parendQO) {
		this.parendQO = parendQO;
	}

	public Boolean getFetchScreeningCondition() {
		return fetchScreeningCondition;
	}

	public void setFetchScreeningCondition(Boolean fetchScreeningCondition) {
		this.fetchScreeningCondition = fetchScreeningCondition;
	}

	public Boolean getFetchChildren() {
		return fetchChildren;
	}

	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}

	public Boolean getFetchProductCategory() {
		return fetchProductCategory;
	}

	public void setFetchProductCategory(Boolean fetchProductCategory) {
		this.fetchProductCategory = fetchProductCategory;
	}

	public ProductCategoryQO getProductCategoryQO() {
		return productCategoryQO;
	}

	public void setProductCategoryQO(ProductCategoryQO productCategoryQO) {
		this.productCategoryQO = productCategoryQO;
	}
	
	
	
}
