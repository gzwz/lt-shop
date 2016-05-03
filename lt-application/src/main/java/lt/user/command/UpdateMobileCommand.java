package lt.user.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class UpdateMobileCommand extends BaseCommand{
	
	private String userId;
	
	private String newMobile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNewMobile() {
		return newMobile;
	}

	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}
	

}
