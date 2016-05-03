package lt.oneBuy.command;

import gzlazypack.common.component.BaseCommand;

public class CreateCloudCategoryCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	/**
	 * 名称
	 */
	private String name;
	
	private String parentId;
	
	private Integer sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
