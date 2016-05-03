package lt.sitepc.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "floorsService")
@SuppressWarnings("serial")
public class FloorsQO extends BaseQO<String>{

	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private FloorsQO parentQO;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 * 
	 * 
	 */
	
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchFloors;
	
	

	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;



	public FloorsQO getParentQO() {
		return parentQO;
	}



	public void setParentQO(FloorsQO parentQO) {
		this.parentQO = parentQO;
	}



	public Boolean getFetchFloors() {
		return fetchFloors;
	}



	public void setFetchFloors(Boolean fetchFloors) {
		this.fetchFloors = fetchFloors;
	}



	public Integer getOrderBy() {
		return orderBy;
	}



	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
}
