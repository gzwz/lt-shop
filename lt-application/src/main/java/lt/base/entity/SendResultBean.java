package lt.base.entity;
/**
 * 短信发送记录实体类
 * @author Administrator
 *
 */
public class SendResultBean {

	// 接收短信的电话号码
	private String telNo;
	// 短信内容
	private String content;
	// 发送日期Str 格式 yyyy-MM-dd
	private String sendDate;
	// 发送结果【成功，失败】
	private String sendResult;
	// 失败原因
	private String failureReason;
	// 其它详细问题描述【主要用于发送失败时】
	private String otherDes;
	
	
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
	public String getSendResult() {
		return sendResult;
	}
	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getOtherDes() {
		return otherDes;
	}
	public void setOtherDes(String otherDes) {
		this.otherDes = otherDes;
	}
	
}
