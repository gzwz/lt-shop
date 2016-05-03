package lt.sitemsg.command.sendplan;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;

/**
 * 修改站内消息发送计划
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class ModifyMsgSendPlanCommand extends BaseCommand {

	private String msgSendPlanId;

	/**
	 * 发送时间
	 */
	private Date sendDate;

	/**
	 * 用的模版id
	 */
	private String msgTemplateId;

	/**
	 * 在发送时套在模版里的变量集，每个接收者都是用这套变量套出正文 如 ['夏日促销','人民广场110号'] sendDate发送时将替换{C}
	 */
	private String[] params;

	public String getMsgSendPlanId() {
		return msgSendPlanId;
	}

	public void setMsgSendPlanId(String msgSendPlanId) {
		this.msgSendPlanId = msgSendPlanId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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

}
