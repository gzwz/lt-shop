package lt.sitemsg.command.notice;

/**
 * 删除站内信
 * 状态改为已删除
 * 
 * @author yuxx
 * 
 */
public class RemoveMsgNoticeCommand {

	private String msgNoticeId;

	public String getMsgNoticeId() {
		return msgNoticeId;
	}

	public void setMsgNoticeId(String msgNoticeId) {
		this.msgNoticeId = msgNoticeId;
	}

}
