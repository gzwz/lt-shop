package lt.virtualAccount.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 虚拟货币查询条件
 * 
 * 
 */
@QueryConfig(daoBeanId = "vaCurrencyService")
@SuppressWarnings("serial")
public class VACurrencyQO extends BaseQO<String> {

	@QueryCondition(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
