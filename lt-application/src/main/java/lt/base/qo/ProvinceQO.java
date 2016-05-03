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
@QueryConfig(daoBeanId = "provinceService")
@SuppressWarnings("serial")
public class ProvinceQO extends BaseQO<String> {

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

 
	
}
