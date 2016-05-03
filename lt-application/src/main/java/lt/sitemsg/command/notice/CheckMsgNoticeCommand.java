package lt.sitemsg.command.notice;

/**
 * 查看某用户是否有未收站内信
 * 状态改为已看见
 * 
 * @author yuxx
 * 
 */
public class CheckMsgNoticeCommand {

	/**
	 * 用户
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
