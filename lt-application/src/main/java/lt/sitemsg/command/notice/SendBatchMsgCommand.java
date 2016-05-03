package lt.sitemsg.command.notice;

import gzlazypack.common.component.BaseCommand;

/**
 * 发送批量站内信
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class SendBatchMsgCommand extends BaseCommand {

	/**
	 * 发送的是哪个计划的站内信通知
	 */
	private String msgSendPlanId;

	public String getMsgSendPlanId() {
		return msgSendPlanId;
	}

	public void setMsgSendPlanId(String msgSendPlanId) {
		this.msgSendPlanId = msgSendPlanId;
	}
	
	
}
