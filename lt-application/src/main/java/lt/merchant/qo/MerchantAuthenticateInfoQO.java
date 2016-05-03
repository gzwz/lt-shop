package lt.merchant.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "merchantAuthenticateInfoService")
@SuppressWarnings("serial")
public class MerchantAuthenticateInfoQO extends BaseQO<String>{
	
	@QueryCondition(name = "merchant", type = QueryConditionType.LEFT_JOIN)
	private MerchantQO merchantQO;

	public MerchantQO getMerchantQO() {
		return merchantQO;
	}

	public void setMerchantQO(MerchantQO merchantQO) {
		this.merchantQO = merchantQO;
	}
	
}
