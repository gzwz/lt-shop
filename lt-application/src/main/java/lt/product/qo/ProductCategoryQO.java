package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "productCategoryService")
public class ProductCategoryQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private ProductCategoryQO parendQO;
	
	@QueryCondition(name = "name")
	private String name;
	
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductCategory;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	
	
	
	/**
	 *  排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	private String productCategoryId;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}


	public Boolean getFetchProductCategory() {
		return fetchProductCategory;
	}

	public void setFetchProductCategory(Boolean fetchProductCategory) {
		this.fetchProductCategory = fetchProductCategory;
	}

	public Boolean getFetchChildren() {
		return fetchChildren;
	}

	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public ProductCategoryQO getParendQO() {
		return parendQO;
	}

	public void setParendQO(ProductCategoryQO parendQO) {
		this.parendQO = parendQO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
