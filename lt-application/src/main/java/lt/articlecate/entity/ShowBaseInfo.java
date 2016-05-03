package lt.articlecate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lt.merchant.entity.M;

@Embeddable
public class ShowBaseInfo {
	
	/**
	 * 文章标题
	 */
	@Column(name = "title",length = 255)
	private String title;
	
	
	/**
	 *  文章内容
	 */
	@Column(name = "content",columnDefinition = M.TEXT_COLUMN)
	private String content;


	/**
	 * 附标题
	 */
	@Column(name = "sub_title",length = 255)
	private String subTitle;
	/**
	 * 简介
	 */
	@Column(name = "brief",length = 65535)
	private String brief;
	
	
	/**
	 * 发表时间
	 */
	@Column(name = "create_time", columnDefinition = M.DATE_COLUMN)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time",columnDefinition = M.DATE_COLUMN)
	private Date updateTime;
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	

}
