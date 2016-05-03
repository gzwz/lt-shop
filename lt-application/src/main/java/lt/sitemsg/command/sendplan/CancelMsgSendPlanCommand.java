package lt.sitemsg.command.sendplan;

import gzlazypack.common.component.BaseCommand;

/**
 * 取消站内消息发送计划 直接删除
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class CancelMsgSendPlanCommand extends BaseCommand {

	private String msgSendPlanId;

	public String getMsgSendPlanId() {
		return msgSendPlanId;
	}

	public void setMsgSendPlanId(String msgSendPlanId) {
		this.msgSendPlanId = msgSendPlanId;
	}

}
