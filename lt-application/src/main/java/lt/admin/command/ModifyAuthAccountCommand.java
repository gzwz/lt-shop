package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 修改帐号组信息
 * 
 * @author yuxiaoxiang
 * 
 */
@SuppressWarnings("serial")
public class ModifyAuthAccountCommand extends BaseCommand {

	private String authAccountId;

	private String loginName;

	private String password;

	private String group_id;

	public String getAuthAccountId() {
		return authAccountId;
	}

	public void setAuthAccountId(String authAccountId) {
		this.authAccountId = authAccountId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
}
