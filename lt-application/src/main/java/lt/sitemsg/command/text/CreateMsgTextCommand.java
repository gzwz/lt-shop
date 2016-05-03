package lt.sitemsg.command.text;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;

/**
 * 创建信件正文，已填入群发通用参数
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class CreateMsgTextCommand extends BaseCommand {

	/**
	 * 邮件标题
	 */
	private String title;

	/**
	 * 在MsgSendPlan执行时生成的消息正文，已经套过通用变量，还保留个人专有变量占位符
	 * 
	 * 尊敬的{P}您好，本周末将举行夏日促销活动，地址是人民广场110号，欢迎光临，你的优惠码是{P}
	 */
	private String content;

	/**
	 * 消息过期时间
	 */
	private Date pastDueDate;

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
