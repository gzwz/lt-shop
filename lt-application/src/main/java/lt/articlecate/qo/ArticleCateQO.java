package lt.articlecate.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "articleCateService")
public class ArticleCateQO extends BaseQO<String>{
	
	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "name")
	private String name;
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private ArticleCateQO parentQO;
	
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchArticleCate;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	private String parentName;
	
	private String title;
	
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public Integer getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getFetchArticleCate() {
		return fetchArticleCate;
	}

	public void setFetchArticleCate(Boolean fetchArticleCate) {
		this.fetchArticleCate = fetchArticleCate;
	}

	public Boolean getFetchChildren() {
		return fetchChildren;
	}

	public void setFetchChildren(Boolean fetchChildren) {
		this.fetchChildren = fetchChildren;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArticleCateQO getParentQO() {
		return parentQO;
	}

	public void setParentQO(ArticleCateQO parentQO) {
		this.parentQO = parentQO;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
