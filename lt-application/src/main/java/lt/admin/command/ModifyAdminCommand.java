package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;

/**
 * 修改管理员
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class ModifyAdminCommand extends BaseCommand {

	private String adminId;

	private String name;

	private String telephone;

	private List<String> roleIds;

	private String loginName;

	private String password;
	
	private String roleIdsChecked;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
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

	public String getRoleIdsChecked() {
		return roleIdsChecked;
	}

	public void setRoleIdsChecked(String roleIdsChecked) {
		this.roleIdsChecked = roleIdsChecked;
	}

	
}
