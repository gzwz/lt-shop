package lt.sitemsg.command.text;

import gzlazypack.common.component.BaseCommand;

/**
 * 将某封站内信设为过期，未见过该站内信通知的客户将再也收不到，对应的状态为未看见过的MsgNotice将被置为删除
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class CloseMsgTextCommand extends BaseCommand {

	private String msgTextId;

	public String getMsgTextId() {
		return msgTextId;
	}

	public void setMsgTextId(String msgTextId) {
		this.msgTextId = msgTextId;
	}

}
