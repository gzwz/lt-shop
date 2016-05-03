package lt.sitemsg.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 查询消息发送计划
 * 
 * @author yuxiaoxiang
 * 
 */
@QueryConfig(daoBeanId = "msgSendPlanDao")
@SuppressWarnings("serial")
public class MsgSendPlanQO extends BaseQO<String> {

	@QueryCondition(name = "template", type = QueryConditionType.FETCH_EAGER)
	private boolean fetchMsgTemplate;

	public boolean isFetchMsgTemplate() {
		return fetchMsgTemplate;
	}

	public void setFetchMsgTemplate(boolean fetchMsgTemplate) {
		this.fetchMsgTemplate = fetchMsgTemplate;
	}

}
