package lt.user.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class UpdatePasswordCommand extends BaseCommand{

	private String UserId;
	
	private String newPassword;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
