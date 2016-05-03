package lt.base.entity;
/**
 * 短信实体类
 * @author l.wz
 *
 */
public class SMSBean {
	
	// 手机号码
	private String telNo;
	// 短信内容
	private String content;
	// 发送日期Str 格式 yyyy-MM-dd
	private String sendDate;
	
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
}
