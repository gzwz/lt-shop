package lt.content.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lt.base.entity.DomainLink;

@Embeddable
public class PostBaseInfo {

	/**
	 * 标题
	 */
	@Column(name = "TITLE")
	private String title;

	/**
	 * 内容正文
	 */
	@Column(name = "CONTENT", columnDefinition = M.TEXT_COLUMN)
	private String content;

	/**
	 * 发布用户
	 */
	private DomainLink author;
	
	/**
	 * 创建日期
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	protected Date createDate;

	public DomainLink getAuthor() {
		return author;
	}

	public void setAuthor(DomainLink author) {
		this.author = author;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
