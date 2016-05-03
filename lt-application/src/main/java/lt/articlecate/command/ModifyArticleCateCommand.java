package lt.articlecate.command;

import gzlazypack.common.component.BaseCommand;

public class ModifyArticleCateCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	private String articleCateId;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父类目
	 */
	private String parentId;
	
	private Integer sort;
	
 

	public String getArticleCateId() {
		return articleCateId;
	}

	public void setArticleCateId(String articleCateId) {
		this.articleCateId = articleCateId;
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
