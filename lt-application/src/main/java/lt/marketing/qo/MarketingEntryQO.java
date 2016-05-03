package lt.marketing.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;


/**
 * 
 *wxp
 */
@QueryConfig(daoBeanId = "marketingEntryService")
@SuppressWarnings("serial")
public class MarketingEntryQO extends BaseQO<String> {

	@QueryCondition(name = "subject", type = QueryConditionType.LEFT_JOIN)
	private MarketingSubjectQO subjectQO;

	@QueryCondition(name = "target", type = QueryConditionType.LEFT_JOIN)
	private MarketingTargetQO targetQO;
	
	@QueryCondition(name = "subject", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchSubject;
	

	public MarketingSubjectQO getSubjectQO() {
		return subjectQO;
	}

	public void setSubjectQO(MarketingSubjectQO subjectQO) {
		this.subjectQO = subjectQO;
	}

	public MarketingTargetQO getTargetQO() {
		return targetQO;
	}

	public void setTargetQO(MarketingTargetQO targetQO) {
		this.targetQO = targetQO;
	}

	public Boolean getFetchSubject() {
		return fetchSubject;
	}

	public void setFetchSubject(Boolean fetchSubject) {
		this.fetchSubject = fetchSubject;
	}
	
}
