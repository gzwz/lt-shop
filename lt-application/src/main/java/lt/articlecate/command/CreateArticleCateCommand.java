package lt.articlecate.command;

import java.util.Date;

import gzlazypack.common.component.BaseCommand;

public class CreateArticleCateCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父类目
	 */
	private String parentId;
	
	private Integer sort;
	
	/**
	 * wz
	 * @return
	 * 根据时间排序
	 */
	private Date createTime;
	

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

 

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}
