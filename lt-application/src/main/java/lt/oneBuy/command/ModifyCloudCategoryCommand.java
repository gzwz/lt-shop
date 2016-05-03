package lt.oneBuy.command;

import gzlazypack.common.component.BaseCommand;

public class ModifyCloudCategoryCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
    private String cloudCategoryId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父类目
	 */
	private String parentId;
	
	/**
	 * 排序
	 */
	private Integer sort;

	public String getCloudCategoryId() {
		return cloudCategoryId;
	}

	public void setCloudCategoryId(String cloudCategoryId) {
		this.cloudCategoryId = cloudCategoryId;
	}

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
