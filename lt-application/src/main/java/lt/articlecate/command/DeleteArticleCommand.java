package lt.articlecate.command;

import lt.articlecate.entity.ArticleCate;
import gzlazypack.common.component.BaseCommand;

public class DeleteArticleCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	/**
	 * 文章id
	 */
	private String articleId;
	
	/**
	 * 文章分类ID
	 */
	private ArticleCate articleCate;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public ArticleCate getArticleCate() {
		return articleCate;
	}

	public void setArticleCate(ArticleCate articleCate) {
		this.articleCate = articleCate;
	}
	
	
}
