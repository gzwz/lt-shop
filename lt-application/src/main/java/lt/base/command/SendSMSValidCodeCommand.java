package lt.base.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 发送短信获取验证码
 * 命令处理完毕后会返回一个流程id，做为本次验证流程的标识
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class SendSMSValidCodeCommand extends BaseCommand {

	/**
	 * 手机号
	 */
	private String mobile;
	
	/** 短信类型
	 *  -1 定义为失败
	 * （1：注册验证码；2：变更密码验证码；3：车辆信息绑定验证码；
	 * 4：车辆信息解绑验证码；5：在线支付通知；6：保险过期通知；
	 * 7：运单签收）不传此参数将发送测试短信 ;8:平台预警;9:签收验证码
	 * 10：送货节点短信
	 */
	private short type;
	
	/** 验证码 */
	private String validCode;
	/** 内容 */
	private String content;
	/** 添加时间 */
	private Long addTime;
	// 发送日期Str 格式 yyyy-MM-dd
	private String sendDate;
	
	/**
	 * 业务场景，如注册、找回密码等
	 */
	private String scene;

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
	
	

}
