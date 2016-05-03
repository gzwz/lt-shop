package lt.sitemsg.command.notice;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;

/**
 * 发送单条消息
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class SendSingleMsgCommand extends BaseCommand {

	/**
	 * 创建的是哪个模版的站内信通知
	 */
	private String msgTemplateId;

	/**
	 * 收消息的用户
	 */
	private String userId;

	/**
	 * 用户的专属消息变量 如：['李先生','123456']
	 */
	private String[] params;

	/**
	 * 消息过期时间，超过该时间未看到notice则删除消息
	 */
	private Date pastDueDate;

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public Date getPastDueDate() {
		return pastDueDate;
	}

	public void setPastDueDate(Date pastDueDate) {
		this.pastDueDate = pastDueDate;
	}

}
