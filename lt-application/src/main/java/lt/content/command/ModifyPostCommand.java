package lt.content.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyPostCommand extends BaseCommand {

	private String postId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容正文
	 */
	private String content;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

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

}
