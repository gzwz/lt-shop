package lt.articlecate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lt.articlecate.command.ChangeArticleCommand;
import lt.articlecate.command.CreateArticleCommand;
import lt.articlecate.command.ModifyArticleCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ARTICLE")
public class Article extends StringIdBaseEntity {

	/**
	 * 文章分类ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cate_id")
	private ArticleCate articleCate;

	/**
	 * 来源信息
	 */
	private ResourseBaseInfo resourseInfo;

	/**
	 * 基本显示信息
	 */
	private ShowBaseInfo baseInfo;

	/**
	 * seo信息
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private SeoBaseInfo seoInfo;

	/**
	 * 状态信息
	 */
	private StatusInfo status;
	
	/**
	 * 删除标识
	 */
	@Type(type = "yes_no")
	@Column(name = "is_delete")
	private Boolean isDelete;
	
	
	
	/**
	 * 
	 * @param 改变文章的状态
	 */
	public void changeStatus(ChangeArticleCommand command,Article article){
		article.setId(command.getId());
		if (command.isDelete()){
			article.setIsDelete(false);
		}else { 
			article.setIsDelete(true);
		}
		
	}

	public void createArticle(CreateArticleCommand command,ArticleCate articleCate) {

		setId(UUIDGenerator.getUUID());
		setIsDelete(false);

		setResourseInfo(new ResourseBaseInfo());
		getResourseInfo().setAuther(command.getAuther());
		getResourseInfo().setRelUrl(command.getRelUrl());
		getResourseInfo().setSuorse(command.getSuorse());

		setBaseInfo(new ShowBaseInfo());
		getBaseInfo().setTitle(command.getTitle());
		getBaseInfo().setSubTitle(command.getSubTitle());
		getBaseInfo().setBrief(command.getBrief());
		getBaseInfo().setContent(command.getContent());
		getBaseInfo().setCreateTime(new Date());
		getBaseInfo().setUpdateTime(new Date());

		
		
		
		setArticleCate(articleCate);

	}

	public void modify(ModifyArticleCommand command,ArticleCate articleCate) {
		
		setIsDelete(false);
		getResourseInfo().setAuther(command.getAuther());
		getResourseInfo().setRelUrl(command.getRelUrl());
		getResourseInfo().setSuorse(command.getSuorse());

		getBaseInfo().setTitle(command.getTitle());
		getBaseInfo().setSubTitle(command.getSubTitle());
		getBaseInfo().setBrief(command.getBrief());
		getBaseInfo().setContent(command.getContent());
		getBaseInfo().setUpdateTime(new Date());

		
		setArticleCate(articleCate);
		
	}

 

	public ArticleCate getArticleCate() {
		return articleCate;
	}

	public void setArticleCate(ArticleCate articleCate) {
		this.articleCate = articleCate;
	}

	public ResourseBaseInfo getResourseInfo() {
		return resourseInfo;
	}

	public void setResourseInfo(ResourseBaseInfo resourseInfo) {
		this.resourseInfo = resourseInfo;
	}

	public ShowBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(ShowBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public SeoBaseInfo getSeoInfo() {
		return seoInfo;
	}

	public void setSeoInfo(SeoBaseInfo seoInfo) {
		this.seoInfo = seoInfo;
	}

	public StatusInfo getStatus() {
		return status;
	}

	public void setStatus(StatusInfo status) {
		this.status = status;
	}
	
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

}
