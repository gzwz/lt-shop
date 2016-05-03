package lt.oneBuy.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "cloudBrandService")
public class CloudBrandQO extends BaseQO<String>{

	@QueryCondition(name = "cloudCategorys", type = QueryConditionType.LEFT_JOIN)
	private CloudCategoryQO cloudCategoryQO;
	
	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private CloudBrandQO parentQO;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 * 
	 * 
	 */
	
	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCloudBrand;
	
	@QueryCondition(name = "cloudCategorys", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCloudCategory;
	
	private String cloudCategoryIds;

	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public CloudCategoryQO getCloudCategoryQO() {
		return cloudCategoryQO;
	}

	public void setCloudCategoryQO(CloudCategoryQO cloudCategoryQO) {
		this.cloudCategoryQO = cloudCategoryQO;
	}

	public CloudBrandQO getParentQO() {
		return parentQO;
	}

	public void setParentQO(CloudBrandQO parentQO) {
		this.parentQO = parentQO;
	}

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

	public String getCloudCategoryIds() {
		return cloudCategoryIds;
	}

	public void setCloudCategoryIds(String cloudCategoryIds) {
		this.cloudCategoryIds = cloudCategoryIds;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
	

}
