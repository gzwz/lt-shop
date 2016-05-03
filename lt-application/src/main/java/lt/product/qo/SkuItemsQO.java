package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "skuItemsService")
public class SkuItemsQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "product", type = QueryConditionType.LEFT_JOIN)
	private ProductQO productQO;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "product", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProduct;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "parameterTime", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public ProductQO getProductQO() {
		return productQO;
	}

	public void setProductQO(ProductQO productQO) {
		this.productQO = productQO;
	}

	public Boolean getFetchProduct() {
		return fetchProduct;
	}

	public void setFetchProduct(Boolean fetchProduct) {
		this.fetchProduct = fetchProduct;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
