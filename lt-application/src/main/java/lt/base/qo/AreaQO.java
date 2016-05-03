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
@QueryConfig(daoBeanId = "areaService")
@SuppressWarnings("serial")
public class AreaQO extends BaseQO<String> {

	@QueryCondition(name = "city", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCity;

	@QueryCondition(name = "city", type = QueryConditionType.LEFT_JOIN)
	private CityQO cityQO;

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

	public Boolean getFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(Boolean fetchCity) {
		this.fetchCity = fetchCity;
	}

	public CityQO getCityQO() {
		return cityQO;
	}

	public void setCityQO(CityQO cityQO) {
		this.cityQO = cityQO;
	}

}
