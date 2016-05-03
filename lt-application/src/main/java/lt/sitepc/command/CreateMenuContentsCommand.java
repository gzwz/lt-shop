package lt.sitepc.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateMenuContentsCommand extends BaseCommand{

     private String bottomMenuId;

	
	private String content;


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
	
	
}
