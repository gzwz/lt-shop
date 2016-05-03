package lt.content.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@QueryConfig(daoBeanId = "postService")
@SuppressWarnings("serial")
public class PostQO extends BaseQO<String> {

	@QueryCondition(name = "forum", type = QueryConditionType.LEFT_JOIN)
	private ForumQO forumQO;
	
	@QueryCondition(name = "show")
	private Boolean show;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	public ForumQO getForumQO() {
		return forumQO;
	}

	public void setForumQO(ForumQO forumQO) {
		this.forumQO = forumQO;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	

}
