package lt.sitepc.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateBottoMenuCommand extends BaseCommand{

	private String name;

	private String remark;
	
	private String parentId;
	
	private String icon;
	
	private Integer sort;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
