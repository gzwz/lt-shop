package lt.articlecate.command;

import java.util.Date;

import lt.articlecate.entity.ArticleCate;
import gzlazypack.common.component.BaseCommand;

public class ModifyArticleCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	private String articleId;
	
	private String seoBaseInfoId;
	
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 *  文章内容
	 */
	private String content;
	/**
	 * 文章分类ID
	 */
	private ArticleCate articleCate;
	/**
	 * 发表时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 有效性标识符
	 */
	private Boolean isEffect;
	
	/**
	 * 自动跳转的外 引用外链地址
	 */
	private String relUrl;
	
	/**
	 * 文章分类ID
	 */
	private String parentId;
	
	/**
	 * 有效性标识符
	 */
	private Boolean isRelUrl;
	
	/**
	 * 作者
	 */
	private String auther;
	/**
	 * 来源
	 */
	private String suorse;
	
	/**
	 * 删除标识
	 */
	private Boolean isDelete;
	/**
	 * 点击量
	 */
	private Integer clickCount;
	/**
	 * 排序 由大到小
	 */
	private Integer sort;
	/**
	 * 自定义seo页面标题
	 */
	private String seoTitle;
	/**
	 * 自定义seo页面keyword
	 */
	private String seoKeyword;
	/**
	 * 自定义seo页面标述
	 */
	private String seoDescription;

	/**
	 * 附标题
	 */
	private String subTitle;
	/**
	 * 简介
	 */
	private String brief;
	/**
	 * 本周必读
	 */
	private Boolean isWeek;
	/**
	 * 热门
	 */
	private Boolean isHot;
	
	/**
	 * 特标
	 */
	private Boolean isSpecial;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	public ArticleCate getArticleCate() {
		return articleCate;
	}

	public void setArticleCate(ArticleCate articleCate) {
		this.articleCate = articleCate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	public String getRelUrl() {
		return relUrl;
	}

	public void setRelUrl(String relUrl) {
		this.relUrl = relUrl;
	}

	public Boolean getIsRelUrl() {
		return isRelUrl;
	}

	public void setIsRelUrl(Boolean isRelUrl) {
		this.isRelUrl = isRelUrl;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getSuorse() {
		return suorse;
	}

	public void setSuorse(String suorse) {
		this.suorse = suorse;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Boolean getIsWeek() {
		return isWeek;
	}

	public void setIsWeek(Boolean isWeek) {
		this.isWeek = isWeek;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSeoBaseInfoId() {
		return seoBaseInfoId;
	}

	public void setSeoBaseInfoId(String seoBaseInfoId) {
		this.seoBaseInfoId = seoBaseInfoId;
	}
	
	
	
	
}
