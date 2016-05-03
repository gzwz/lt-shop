package lt.base.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

import java.util.Date;

@QueryConfig(daoBeanId = "smsValidateRecordServiceImpl")
@SuppressWarnings("serial")
public class SMSValidateSagaQO extends BaseQO<String> {

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
