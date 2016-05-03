package lt.merchant.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "merchantCategoryService")
@SuppressWarnings("serial")
public class MerchantCategoryQO extends BaseQO<String>{

	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchMerchantCategory;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "createTime", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getFetchMerchantCategory() {
		return fetchMerchantCategory;
	}

	public void setFetchMerchantCategory(Boolean fetchMerchantCategory) {
		this.fetchMerchantCategory = fetchMerchantCategory;
	}

	public Boolean getFetchChildren() {
		return fetchChildren;
	}

	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}
	
	
	
}
