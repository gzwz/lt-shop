package lt.sitemsg.qo;

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
public class MsgTextQO extends BaseQO<String> {

	private boolean fetchMsgTemplate;

	public boolean isFetchMsgTemplate() {
		return fetchMsgTemplate;
	}

	public void setFetchMsgTemplate(boolean fetchMsgTemplate) {
		this.fetchMsgTemplate = fetchMsgTemplate;
	}

}
