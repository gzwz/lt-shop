package lt.sitepc.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyMenuContentsCommand extends BaseCommand{
	
	private String bottomMenuId;

	
	private String content;
	
	private String menuContentsId;


	public String getBottomMenuId() {
		return bottomMenuId;
	}


	public void setBottomMenuId(String bottomMenuId) {
		this.bottomMenuId = bottomMenuId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getMenuContentsId() {
		return menuContentsId;
	}


	public void setMenuContentsId(String menuContentsId) {
		this.menuContentsId = menuContentsId;
	}
	
	
}
