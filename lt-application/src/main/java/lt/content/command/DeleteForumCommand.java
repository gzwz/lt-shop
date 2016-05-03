package lt.content.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 删除版块
 * 
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class DeleteForumCommand extends BaseCommand {

	private String forumId;

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

}
