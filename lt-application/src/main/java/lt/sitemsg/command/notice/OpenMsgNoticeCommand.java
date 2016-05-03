package lt.sitemsg.command.notice;

/**
 * 打开站内信 状态改为已打开
 * 
 * @author yuxx
 * 
 */
public class OpenMsgNoticeCommand {

	private String msgNoticeId;

	private String[] params;

	public String getMsgNoticeId() {
		return msgNoticeId;
	}

	public void setMsgNoticeId(String msgNoticeId) {
		this.msgNoticeId = msgNoticeId;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
