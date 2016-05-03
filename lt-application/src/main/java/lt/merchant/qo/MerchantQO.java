package lt.merchant.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "merchantService")
@SuppressWarnings("serial")
public class MerchantQO extends BaseQO<String>{
	
	@QueryCondition(name = "merchantCategory", type = QueryConditionType.LEFT_JOIN)
	private MerchantCategoryQO merchantCategoryQO;
	
	@QueryCondition(name = "baseInfo.name", ifTrueUseLike = "nameLike")
	private String name;
	
	
	@QueryCondition(name = "domainLink.domainType",type=QueryConditionType.NE)
	private String domainType;
	
	@QueryCondition(name = "merchantCategory", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchMerchantCategory;
	
	private Boolean nameLike;

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

	public MerchantCategoryQO getMerchantCategoryQO() {
		return merchantCategoryQO;
	}

	public void setMerchantCategoryQO(MerchantCategoryQO merchantCategoryQO) {
		this.merchantCategoryQO = merchantCategoryQO;
	}

	public Boolean getFetchMerchantCategory() {
		return fetchMerchantCategory;
	}

	public void setFetchMerchantCategory(Boolean fetchMerchantCategory) {
		this.fetchMerchantCategory = fetchMerchantCategory;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
	
	
	
}
