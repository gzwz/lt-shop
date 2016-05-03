package lt.content.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "navigationItemService")
@SuppressWarnings("serial")
public class NavigationItemQO extends BaseQO<String> {
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	
	private String productCatoryId;
	
	private String productCatoryName;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getProductCatoryId() {
		return productCatoryId;
	}

	public void setProductCatoryId(String productCatoryId) {
		this.productCatoryId = productCatoryId;
	}

	public String getProductCatoryName() {
		return productCatoryName;
	}

	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}
	
	
	
	
}
