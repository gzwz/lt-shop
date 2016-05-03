package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;

/**
 * 修改角色
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class ModifyRoleCommand extends BaseCommand {

	private String roleId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 拥有授权的功能
	 */
	private List<String> resourceIds;
	
	private String ids;

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	

}
