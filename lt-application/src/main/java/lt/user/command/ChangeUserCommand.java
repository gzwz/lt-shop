package lt.user.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ChangeUserCommand extends BaseCommand{
	
	private String userId;
	
	private String isEnable;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
}
