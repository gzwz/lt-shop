package lt.base.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.component.BaseQO;

import java.util.Date;

@SuppressWarnings("serial")
public class SMSValidateRecordQO extends BaseQO<String> {

	@QueryCondition(name = "saga", type = QueryConditionType.FETCH_EAGER)
	private boolean fetchSMSValidateSaga;
	
	/**
	 * 最早创建时间
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.GE)
	private Date geCreateDate;

	/**
	 * 最晚创建时间
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.LE)
	private Date leCreateDate;

	public boolean isFetchSMSValidateSaga() {
		return fetchSMSValidateSaga;
	}

	public void setFetchSMSValidateSaga(boolean fetchSMSValidateSaga) {
		this.fetchSMSValidateSaga = fetchSMSValidateSaga;
	}

	public Date getGeCreateDate() {
		return geCreateDate;
	}

	public void setGeCreateDate(Date geCreateDate) {
		this.geCreateDate = geCreateDate;
	}

	public Date getLeCreateDate() {
		return leCreateDate;
	}

	public void setLeCreateDate(Date leCreateDate) {
		this.leCreateDate = leCreateDate;
	}
	
	
}
