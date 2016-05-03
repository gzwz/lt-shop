package lt.sitemsg.command.template;

import gzlazypack.common.component.BaseCommand;

/**
 * 禁用一个站内消息模版
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class DisableMsgTemplateCommand extends BaseCommand {

	private String msgTemplateId;

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

}
