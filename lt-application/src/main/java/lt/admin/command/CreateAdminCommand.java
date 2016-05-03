package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;


/**
 * 创建管理员
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class CreateAdminCommand extends BaseCommand {

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 角色
	 */
	private List<String> roleIds;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 明文密码
	 */
	private String password;

	private String roleIdsChecked;

	private String type;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
