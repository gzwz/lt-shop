package lt.admin.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 
 * @author yuxx
 * 
 */
@QueryConfig(daoBeanId = "roleDao")
@SuppressWarnings("serial")
public class RoleQO extends BaseQO<String> {

	@QueryCondition(name = "position", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchPosition;

	@QueryCondition(name = "resources", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchResources;
	
	@QueryCondition(name = "resources", type = QueryConditionType.FETCH_EAGER)
	private ResourceQO resourceQO;
	
	/**
	 *  排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public Boolean getFetchPosition() {
		return fetchPosition;
	}

	public void setFetchPosition(Boolean fetchPosition) {
		this.fetchPosition = fetchPosition;
	}

	public Boolean getFetchResources() {
		return fetchResources;
	}

	public void setFetchResources(Boolean fetchResources) {
		this.fetchResources = fetchResources;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public ResourceQO getResourceQO() {
		return resourceQO;
	}

	public void setResourceQO(ResourceQO resourceQO) {
		this.resourceQO = resourceQO;
	}


}
