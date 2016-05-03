package lt.sitepc.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "menuContentsService")
@SuppressWarnings("serial")
public class MenuContentsQO extends BaseQO<String>{

	@QueryCondition(name = "bottomMenu", type = QueryConditionType.LEFT_JOIN)
	private BottomMenuQO bottomMenuQO;
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "bottomMenu", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchBottomMenu;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "createTime", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	private String bottomMenuId;

	public BottomMenuQO getBottomMenuQO() {
		return bottomMenuQO;
	}

	public void setBottomMenuQO(BottomMenuQO bottomMenuQO) {
		this.bottomMenuQO = bottomMenuQO;
	}

	public Boolean getFetchBottomMenu() {
		return fetchBottomMenu;
	}

	public void setFetchBottomMenu(Boolean fetchBottomMenu) {
		this.fetchBottomMenu = fetchBottomMenu;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getBottomMenuId() {
		return bottomMenuId;
	}

	public void setBottomMenuId(String bottomMenuId) {
		this.bottomMenuId = bottomMenuId;
	}
	
}
