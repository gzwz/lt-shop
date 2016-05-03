package lt.marketing.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;


/**
 * 
 *
 */
@QueryConfig(daoBeanId = "marketingSubjectService")
@SuppressWarnings("serial")
public class MarketingSubjectQO extends BaseQO<String> {

	@QueryCondition(name = "domainLink.domainId")
	private String domainId;

	@QueryCondition(name = "domainLink.domainType")
	private String domainType;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

}
