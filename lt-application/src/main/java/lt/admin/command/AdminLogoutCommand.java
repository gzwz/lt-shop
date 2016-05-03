package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 登出
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class AdminLogoutCommand extends BaseCommand {

	/**
	 * 用户id
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
