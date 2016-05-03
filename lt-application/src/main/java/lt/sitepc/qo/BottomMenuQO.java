package lt.sitepc.qo;

import lt.sitepc.entity.BottomMenu;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "bottomMenuService")
@SuppressWarnings("serial")
public class BottomMenuQO extends BaseQO<String>{
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private BottomMenuQO parentQO;
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchBottomMenu;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	private BottomMenu parent;
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;
	public BottomMenuQO getParentQO() {
		return parentQO;
	}
	public void setParentQO(BottomMenuQO parentQO) {
		this.parentQO = parentQO;
	}
	public Boolean getFetchChildren() {
		return fetchChildren;
	}
	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}
	public BottomMenu getParent() {
		return parent;
	}
	public void setParent(BottomMenu parent) {
		this.parent = parent;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Boolean getFetchBottomMenu() {
		return fetchBottomMenu;
	}
	public void setFetchBottomMenu(Boolean fetchBottomMenu) {
		this.fetchBottomMenu = fetchBottomMenu;
	}
	
	

}
