package lt.base.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "operationAreaService")
public class OperationAreaQO extends BaseQO<String> {

	@QueryCondition(name = "area", type = QueryConditionType.LEFT_JOIN)
	private AreaQO areaQO;

	@QueryCondition(name = "operationMerchantUserId")
	private String operationMerchantUserId;

	public AreaQO getAreaQO() {
		return areaQO;
	}

	public void setAreaQO(AreaQO areaQO) {
		this.areaQO = areaQO;
	}

	public String getOperationMerchantUserId() {
		return operationMerchantUserId;
	}

	public void setOperationMerchantUserId(String operationMerchantUserId) {
		this.operationMerchantUserId = operationMerchantUserId;
	}

}
