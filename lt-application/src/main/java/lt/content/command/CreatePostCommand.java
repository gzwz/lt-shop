package lt.content.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreatePostCommand extends BaseCommand {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容正文
	 */
	private String content;

	private String authUserId;

	private String authUserName;

	private String forumId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}

	public String getAuthUserName() {
		return authUserName;
	}

	public void setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

}
