package lt.merchant.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
public class MerchantContactInfoQO extends BaseQO<String>{

	@QueryCondition(name = "merchant", type = QueryConditionType.LEFT_JOIN)
	private MerchantQO merchantQO;
	
	/**
	 * 是否立即加载订单明细项集合,创建qo后需要调用qo.setBatchResult(true)
	 */

	@QueryCondition(name = "province", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProvince;

	@QueryCondition(name = "city", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCity;

	@QueryCondition(name = "area", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchArea;

	public MerchantQO getMerchantQO() {
		return merchantQO;
	}

	public void setMerchantQO(MerchantQO merchantQO) {
		this.merchantQO = merchantQO;
	}

	public Boolean getFetchProvince() {
		return fetchProvince;
	}

	public void setFetchProvince(Boolean fetchProvince) {
		this.fetchProvince = fetchProvince;
	}

	public Boolean getFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(Boolean fetchCity) {
		this.fetchCity = fetchCity;
	}

	public Boolean getFetchArea() {
		return fetchArea;
	}

	public void setFetchArea(Boolean fetchArea) {
		this.fetchArea = fetchArea;
	}
	
}
