package lt.base.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
public class ImageQO extends BaseQO<String> {

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
