package lt.sitemsg.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

import java.util.Date;

/**
 * 查询消息发送计划
 * 
 * @author yuxiaoxiang
 * 
 */
@QueryConfig(daoBeanId = "msgNoticeDao")
@SuppressWarnings("serial")
public class MsgNoticeQO extends BaseQO<String> {

	@QueryCondition(name = "msgText", type = QueryConditionType.FETCH_EAGER)
	private boolean fetchMsgText;

	/**
	 * 收消息的用户
	 */
	@QueryCondition(name = "userId")
	private String userId;

	/**
	 * 状态 0未看到 1已看到 2已打开 3已删除
	 */
	@QueryCondition(name = "status")
	private String status;

	/**
	 * 结束报名时间起始
	 */
	@QueryCondition(name = "pastDueDate", type = QueryConditionType.GE)
	private Date gePastDueDate;

	public boolean isFetchMsgText() {
		return fetchMsgText;
	}

	public void setFetchMsgText(boolean fetchMsgText) {
		this.fetchMsgText = fetchMsgText;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGePastDueDate() {
		return gePastDueDate;
	}

	public void setGePastDueDate(Date gePastDueDate) {
		this.gePastDueDate = gePastDueDate;
	}

}
