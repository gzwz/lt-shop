package lt.sitemsg.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.sitemsg.command.notice.SendSingleMsgCommand;

/**
 * 消息通知
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "NOTICE")
@SuppressWarnings("serial")
public class MsgNotice extends StringIdBaseEntity {

	/**
	 * 收消息的用户
	 */
	@Column(name = "USER_ID")
	private String userId;

	/**
	 * 消息正文 尊敬的{P}您好，本周末将举行夏日促销活动，地址是人民广场110号，欢迎光临，你的优惠码是{P}
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MSG_TEXT_ID", nullable = false)
	private MsgText msgText;

	/**
	 * 发送时间
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	/**
	 * 邮件标题，同msgText.title
	 */
	@Column(name = "TITLE", length = 256)
	private String title;

	/**
	 * 消息模板
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MSG_TEMPLATE_ID", nullable = false)
	private MsgTemplate template;

	/**
	 * 消息主题图标
	 */
	@Column(name = "THEME_ICON", length = 512)
	private String themeIcon;

	/**
	 * 状态 0未看到 1已看到 2已打开 3已删除
	 */
	@Column(name = "STATUS", length =20)
	private String status;

	public final static String STATUS_UN_SEE = "un_see";
	public final static String STATUS_SEE = "see";
	public final static String STATUS_OPEN = "open";
	public final static String STATUS_REMOVE = "remove";

	/**
	 * 消息过期时间
	 */
	@Column(name = "PAST_DUE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date pastDueDate;

	/**
	 * 发送单条消息时，已组装好正文，正文msgText是最终消息内容
	 * 
	 * @param command
	 * @param template
	 * @param msgText
	 */
	public void create(SendSingleMsgCommand command, MsgTemplate template,
			MsgText msgText, String themeIcon) {
		setId(UUIDGenerator.getUUID());
		setTitle(template.getTitle());
		setMsgText(msgText);
		setUserId(command.getUserId());
		setTemplate(template);
		setThemeIcon(themeIcon);
		setPastDueDate(msgText.getPastDueDate());
		setStatus(MsgNotice.STATUS_UN_SEE);
		setCreateDate(new Date());
	}

	/**
	 * 按计划发送批量消息时，用户打开消息才组装正文，正文msgText里有变量
	 * 
	 * @param command
	 * @param sendPlan
	 * @param msgText
	 */
	public void create(String userId, MsgSendPlan sendPlan, MsgText msgText,
			String themeIcon) {
		setId(UUIDGenerator.getUUID());
		setTitle(sendPlan.getTemplate().getTitle());
		setMsgText(msgText);
		setUserId(userId);
		setTemplate(sendPlan.getTemplate());
		setThemeIcon(themeIcon);
		setPastDueDate(msgText.getPastDueDate());
		setStatus(MsgNotice.STATUS_UN_SEE);
		setCreateDate(new Date());
	}

	/**
	 * 看到了信件通知，未打开
	 */
	public void see() {
		setStatus(STATUS_SEE);
	}

	/**
	 * 打开了信件
	 */
	public void open() {
		setStatus(STATUS_OPEN);
	}

	/**
	 * 删除
	 */
	public void remove() {
		setStatus(STATUS_REMOVE);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public MsgText getMsgText() {
		return msgText;
	}

	public void setMsgText(MsgText msgText) {
		this.msgText = msgText;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThemeIcon() {
		return themeIcon;
	}

	public void setThemeIcon(String themeIcon) {
		this.themeIcon = themeIcon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPastDueDate() {
		return pastDueDate;
	}

	public void setPastDueDate(Date pastDueDate) {
		this.pastDueDate = pastDueDate;
	}

	public MsgTemplate getTemplate() {
		return template;
	}

	public void setTemplate(MsgTemplate template) {
		this.template = template;
	}

}
