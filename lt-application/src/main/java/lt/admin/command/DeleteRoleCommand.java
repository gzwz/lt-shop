package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 删除角色
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class DeleteRoleCommand extends BaseCommand {
	
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	private String[] roleIds;

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

}
