package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "productParameterValueService")
public class ProductParameterValueQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "parameter", type = QueryConditionType.LEFT_JOIN)
	private ProductParameterQO productParameterQO;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parameter", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProductParameter;
	
	private String productParameterId;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "value", type = QueryConditionType.ORDER)
	private Integer orderBy;

	

	public ProductParameterQO getProductParameterQO() {
		return productParameterQO;
	}

	public void setProductParameterQO(ProductParameterQO productParameterQO) {
		this.productParameterQO = productParameterQO;
	}

	public Boolean getFetchProductParameter() {
		return fetchProductParameter;
	}

	public void setFetchProductParameter(Boolean fetchProductParameter) {
		this.fetchProductParameter = fetchProductParameter;
	}

	public String getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(String productParameterId) {
		this.productParameterId = productParameterId;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
