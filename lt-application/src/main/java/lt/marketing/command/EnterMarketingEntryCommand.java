package lt.marketing.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 进入推广链接，扫二维码或直接点击URL等
 * 将返回token推广令牌给点击的游客
 * 
 * 
 */
@SuppressWarnings("serial")
public class EnterMarketingEntryCommand extends BaseCommand {

	/**
	 * 推广链接入口id
	 */
	private String entryId;

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

}
