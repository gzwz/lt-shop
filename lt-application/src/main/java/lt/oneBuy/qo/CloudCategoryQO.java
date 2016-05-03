package lt.oneBuy.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "cloudCategoryService")
public class CloudCategoryQO extends BaseQO<String> {

	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private CloudCategoryQO parendQO;

	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCloudCategory;

	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;

	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public CloudCategoryQO getParendQO() {
		return parendQO;
	}

	public void setParendQO(CloudCategoryQO parendQO) {
		this.parendQO = parendQO;
	}

	public Boolean getFetchCloudCategory() {
		return fetchCloudCategory;
	}

	public void setFetchCloudCategory(Boolean fetchCloudCategory) {
		this.fetchCloudCategory = fetchCloudCategory;
	}

	public Boolean getFetchChildren() {
		return fetchChildren;
	}

	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}
