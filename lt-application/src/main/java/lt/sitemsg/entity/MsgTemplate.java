package lt.sitemsg.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lt.sitemsg.command.template.CreateMsgTemplateCommand;
import lt.sitemsg.command.template.ModifyMsgTemplateCommand;

/**
 * 站内消息模版
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "TEMPLATE")
@SuppressWarnings("serial")
public class MsgTemplate extends StringIdBaseEntity {

	/**
	 * 名称
	 */
	@Column(name = "NAME", length = 128)
	private String name;

	/**
	 * 消息标题
	 */
	@Column(name = "TITLE", length = 128)
	private String title;

	/**
	 * {P}代表个人专有信息占位符，{C}代表发送批次内容通用信息占位符 模版内容
	 * 如：尊敬的{P}您好，本周末将举行{C}活动，地址是{C}，欢迎光临，你的优惠码是{P}
	 */
	@Column(name = "CONTENT", columnDefinition = M.TEXT_COLUMN)
	private String content;

	/**
	 * 是否需要群发
	 */
	@Type(type = "yes_no")
	@Column(name = "BATCH")
	private Boolean batch;

	/**
	 * 是否启用
	 */
	@Type(type = "yes_no")
	@Column(name = "ENABLE")
	private Boolean enable;

	public void create(CreateMsgTemplateCommand command) {
		setId(UUIDGenerator.getUUID());

		setBatch(command.getBatch());
		setContent(command.getContent());
		setEnable(true);
		setName(command.getName());
		setTitle(command.getTitle());

	}

	public void disable() {
		setEnable(false);
	}

	public void enable() {
		setEnable(true);
	}

	public void modify(ModifyMsgTemplateCommand command) {
		setBatch(command.getBatch());
		setContent(command.getContent());
		setName(command.getName());
		setTitle(command.getTitle());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getBatch() {
		return batch;
	}

	public void setBatch(Boolean batch) {
		this.batch = batch;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
