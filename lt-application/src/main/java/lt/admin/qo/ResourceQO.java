package lt.admin.qo;

import lt.admin.entity.Resource;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 查询资源
 * 
 * @author yuxiaoxiang
 * 
 */
@QueryConfig(daoBeanId = "resourceDao")
@SuppressWarnings("serial")
public class ResourceQO extends BaseQO<String> {

	@QueryCondition(name = "name", ifTrueUseLike = "nameLike")
	private String name;
	
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchResource;
	
	@QueryCondition(name = "children", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchChildren;
	
	private Resource parent;
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	/**
	 * 模糊匹配资源名
	 */
	private Boolean nameLike = false;

	/**
	 * 资源访问路径
	 */
	@QueryCondition(name = "url")
	private String url;

	@QueryCondition(name = "clientType")
	private String clientType;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getNameLike() {
		return nameLike;
	}

	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Boolean getFetchResource() {
		return fetchResource;
	}

	public void setFetchResource(Boolean fetchResource) {
		this.fetchResource = fetchResource;
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

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}
	
	
	
}
