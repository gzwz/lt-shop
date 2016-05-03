package lt.articlecate.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ChangeArticleCommand extends BaseCommand {

	private String id;

	private boolean isDelete;
	
	/**
	 * 文章分类ID
	 */
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

 

}
