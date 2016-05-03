package lt.articlecate.entity;


import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;







import lt.articlecate.command.CreateArticleCommand;
import lt.articlecate.command.ModifyArticleCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "seo_base_info")
public class SeoBaseInfo extends StringIdBaseEntity {

	/**
	 * 自定义seo页面标题
	 */
	@Column(name = "seo_title",columnDefinition = M.TEXT_COLUMN )
	private String seoTitle;
	
	/**
	 * 自定义seo页面keyword
	 */
	@Column(name = "seo_keyword",columnDefinition = M.TEXT_COLUMN)
	private String seoKeyword;
	/**
	 * 自定义seo页面标述
	 */
	@Column(name = "seo_description",columnDefinition = M.TEXT_COLUMN)
	private String seoDescription;
	
	public void createSeoBaseInfo(CreateArticleCommand command,Article article){
		setId(article.getId());
		setSeoTitle(command.getSeoTitle());
		setSeoDescription(command.getSeoDescription());
		setSeoKeyword(command.getSeoKeyword());
	}
	
	public void modifySeoBaseInfo(ModifyArticleCommand command,Article article){
		setSeoTitle(command.getSeoTitle());
		setSeoDescription(command.getSeoDescription());
		setSeoKeyword(command.getSeoKeyword());
	}
	
	
	public String getSeoTitle() {
		return seoTitle;
	}
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	public String getSeoKeyword() {
		return seoKeyword;
	}
	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}
	public String getSeoDescription() {
		return seoDescription;
	}
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	
	
}
