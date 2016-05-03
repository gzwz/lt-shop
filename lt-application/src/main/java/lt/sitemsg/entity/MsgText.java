package lt.sitemsg.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.sitemsg.command.text.CreateMsgTextCommand;

/**
 * 消息正文
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "TEXT")
@SuppressWarnings("serial")
public class MsgText extends StringIdBaseEntity {

	/**
	 * 邮件标题
	 */
	@Column(name = "TITLE", length = 256)
	private String title;

	/**
	 * 在MsgSendPlan执行时生成的消息正文，已经套过通用变量，还保留个人专有变量占位符
	 * 
	 * 尊敬的{P}您好，本周末将举行夏日促销活动，地址是人民广场110号，欢迎光临，你的优惠码是{P}
	 */
	@Column(name = "CONTENT", columnDefinition = M.TEXT_COLUMN)
	private String content;

	/**
	 * 消息过期时间
	 */
	@Column(name = "PAST_DUE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date pastDueDate;
	
	public void create(CreateMsgTextCommand command) {
		setId(UUIDGenerator.getUUID());
		
		setContent(command.getContent());
		setPastDueDate(command.getPastDueDate());
		setTitle(command.getTitle());
	}

	public void close() {
		setPastDueDate(new Date());
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

	public Date getPastDueDate() {
		return pastDueDate;
	}

	public void setPastDueDate(Date pastDueDate) {
		this.pastDueDate = pastDueDate;
	}

}
