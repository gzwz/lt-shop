package lt.virtualAccount.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;


@QueryConfig(daoBeanId = "virtualAccountChangeDetailService")
@SuppressWarnings("serial")
public class VirtualAccountChangeDetailQO extends BaseQO<String> {

	/**
	 * 业务类型
	 */
	@QueryCondition(name = "businessType")
	private String businessType;
	
	@QueryCondition(name = "virtualAccount", type = QueryConditionType.LEFT_JOIN)
	private VirtualAccountQO virtualAccountQO;
	
	@QueryCondition(name = "virtualAccount", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchVirtualAccount;


	private String virtualAccountId;
	
	/**
	 * 变更明细类型
	 */
	@QueryCondition(name = "type")
	private String type;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getFetchVirtualAccount() {
		return fetchVirtualAccount;
	}

	public void setFetchVirtualAccount(Boolean fetchVirtualAccount) {
		this.fetchVirtualAccount = fetchVirtualAccount;
	}

	public VirtualAccountQO getVirtualAccountQO() {
		return virtualAccountQO;
	}

	public void setVirtualAccountQO(VirtualAccountQO virtualAccountQO) {
		this.virtualAccountQO = virtualAccountQO;
	}

	public String getVirtualAccountId() {
		return virtualAccountId;
	}

	public void setVirtualAccountId(String virtualAccountId) {
		this.virtualAccountId = virtualAccountId;
	}

	
}
