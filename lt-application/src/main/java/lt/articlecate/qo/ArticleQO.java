package lt.articlecate.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "seoBaseInfoService")
public class ArticleQO extends BaseQO<String>{
	
	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "articleCate", type = QueryConditionType.LEFT_JOIN)
	private ArticleCateQO articleCateQO;
	
	
	@QueryCondition(name = "articleCate", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchArticleCate;
	
	
	@QueryCondition(name = "isDelete")
	private Boolean isDelete;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "baseInfo.createTime", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	private String artickeCateId;
	
	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ArticleCateQO getArticleCateQO() {
		return articleCateQO;
	}

	public void setArticleCateQO(ArticleCateQO articleCateQO) {
		this.articleCateQO = articleCateQO;
	}

	public String getArtickeCateId() {
		return artickeCateId;
	}

	public void setArtickeCateId(String artickeCateId) {
		this.artickeCateId = artickeCateId;
	}

	public Boolean getFetchArticleCate() {
		return fetchArticleCate;
	}

	public void setFetchArticleCate(Boolean fetchArticleCate) {
		this.fetchArticleCate = fetchArticleCate;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

}
