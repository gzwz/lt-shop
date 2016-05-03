package lt.sitemsg.command.sendplan;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;

/**
 * 创建站内消息发送计划
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class CreateMsgSendPlanCommand extends BaseCommand {

	/**
	 * 发送时间
	 */
	private Date sendDate;

	/**
	 * 信件失效时间，发送后到该时间仍未被看见将再也收不到
	 */
	private Date pastDueDate;

	/**
	 * 发送的用户
	 */
	private String[] userIds;

	/**
	 * 用的模版id
	 */
	private String msgTemplateId;

	/**
	 * 在发送时套在模版里的变量集，每个接收者都是用这套变量套出正文 如 ['夏日促销','人民广场110号'] sendDate发送时将替换{C}
	 */
	private String[] params;

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
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
