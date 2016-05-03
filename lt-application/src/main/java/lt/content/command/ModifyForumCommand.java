package lt.content.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyForumCommand extends BaseCommand {

	private String forumId;

	/**
	 * 版块名称
	 */
	private String name;

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
