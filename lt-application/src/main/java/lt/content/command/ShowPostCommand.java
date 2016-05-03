package lt.content.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 显示贴子
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class ShowPostCommand extends BaseCommand {

	private String postId;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

}
