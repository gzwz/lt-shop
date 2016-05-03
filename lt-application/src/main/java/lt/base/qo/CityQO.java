package lt.base.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 
 * @author yuxx
 * 
 */
@QueryConfig(daoBeanId = "cityService")
@SuppressWarnings("serial")
public class CityQO extends BaseQO<String> {

	@QueryCondition(name = "province", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProvince;

	@QueryCondition(name = "province", type = QueryConditionType.LEFT_JOIN)
	private ProvinceQO provinceQO;

	@QueryCondition(name = "name", ifTrueUseLike = "nameLike")
	private String name;

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

	public Boolean getFetchProvince() {
		return fetchProvince;
	}

	public void setFetchProvince(Boolean fetchProvince) {
		this.fetchProvince = fetchProvince;
	}

	public ProvinceQO getProvinceQO() {
		return provinceQO;
	}

	public void setProvinceQO(ProvinceQO provinceQO) {
		this.provinceQO = provinceQO;
	}

}
