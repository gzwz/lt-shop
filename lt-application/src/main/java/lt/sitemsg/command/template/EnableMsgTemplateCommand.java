package lt.sitemsg.command.template;

import gzlazypack.common.component.BaseCommand;

/**
 * 启用一个站内消息模版
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class EnableMsgTemplateCommand extends BaseCommand {

	private String msgTemplateId;

	public String getMsgTemplateId() {
		return msgTemplateId;
	}

	public void setMsgTemplateId(String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

}
