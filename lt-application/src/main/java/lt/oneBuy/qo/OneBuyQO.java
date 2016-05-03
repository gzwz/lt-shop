package lt.oneBuy.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "oneBuyService")
public class OneBuyQO extends BaseQO<String>{

	@QueryCondition(name = "cloudBrand", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCloudBrand;
	
	@QueryCondition(name = "cloudCategory", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCloudCategory;
	
	@QueryCondition(name = "baseInfo.name", ifTrueUseLike = "nameLike")
	private String name;
	
	@QueryCondition(name = "baseInfo.hot")
	private Boolean hot;
	
	@QueryCondition(name = "status")
	private String status;
	
	@QueryCondition(name = "showStatus")
	private String showStatus;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "createTime", type = QueryConditionType.ORDER)
	private Integer orderBy;
	
	private Boolean nameLike;


	public Boolean getFetchCloudBrand() {
		return fetchCloudBrand;
	}

	public void setFetchCloudBrand(Boolean fetchCloudBrand) {
		this.fetchCloudBrand = fetchCloudBrand;
	}

	public Boolean getFetchCloudCategory() {
		return fetchCloudCategory;
	}

	public void setFetchCloudCategory(Boolean fetchCloudCategory) {
		this.fetchCloudCategory = fetchCloudCategory;
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

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}


}
