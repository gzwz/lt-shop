package lt.user.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@QueryConfig(daoBeanId = "addressesService")
@SuppressWarnings("serial")
public class AddressesQO extends BaseQO<String> {

	@QueryCondition(name="defaultAddress")
	private Boolean defaultAddress;

	@QueryCondition(name = "user", type = QueryConditionType.LEFT_JOIN)
	private UserQO userQO ;
	
	@QueryCondition(name = "province", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProvince;

	@QueryCondition(name = "city", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCity;

	@QueryCondition(name = "area", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchArea;

	public UserQO getUserQO() {
		return userQO;
	}
	

	public void setUserQO(UserQO userQO) {
		this.userQO = userQO;
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
	public Boolean getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	

}
