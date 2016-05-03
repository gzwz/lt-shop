package lt.content.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.entity.DomainLink;
import lt.content.command.CreatePostCommand;
import lt.content.command.ModifyPostCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * 贴子
 * 
 * @author yuxx
 * 
 */
@Entity
@DynamicUpdate
@Table(name = M.TABLE_PREFIX + "POST")
@SuppressWarnings("serial")
public class Post extends StringIdBaseEntity {

	/**
	 * 文章基本信息
	 */
	protected PostBaseInfo baseInfo;

	/**
	 * 要显示该文章的版块
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORUM_ID", nullable = true)
	private Forum forum;

	@Type(type = "yes_no")
	@Column(name = "IS_SHOW")
	private Boolean show;

	public void create(CreatePostCommand command, Forum forum) {
		setId(UUIDGenerator.getUUID());

		setBaseInfo(new PostBaseInfo());
		getBaseInfo().setAuthor(new DomainLink());
		getBaseInfo().getAuthor().setDomainId(command.getAuthUserId());
		getBaseInfo().getAuthor().setDomainName(command.getAuthUserName());
		getBaseInfo().getAuthor().setDomainType("user");

		getBaseInfo().setTitle(command.getTitle());
		getBaseInfo().setContent(command.getContent());
		getBaseInfo().setCreateDate(new Date());

		setForum(forum);
		setShow(false);
	}

	public void modify(ModifyPostCommand command) {
		getBaseInfo().setTitle(command.getTitle());
		getBaseInfo().setContent(command.getContent());
		setShow(false);
	}
	
	public void show() {
		setShow(true);
	}
	
	public void hide() {
		setShow(false);
	}
	
	public PostBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(PostBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

}
