package lt.sitemsg.command.template;

import gzlazypack.common.component.BaseCommand;

/**
 * 修改一个站内消息模版
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class ModifyMsgTemplateCommand extends BaseCommand {

	private String msgTemplateId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 消息标题
	 */
	private String title;

	/**
	 * 模版内容 如：尊敬的{P}您好，本周末将举行{C}活动，地址是{C}，欢迎光临，你的优惠码是{P}
	 */
	private String content;

	/**
	 * 消息类型
	 */
	private Integer msgType;

	/**
	 * 是否需要群发
	 */
	private Boolean batch;

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
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

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Boolean getBatch() {
		return batch;
	}

	public void setBatch(Boolean batch) {
		this.batch = batch;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
