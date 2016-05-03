package lt.base.event;

import gzlazypack.common.component.BaseEvent;

/**
 * 手机验证码发送成功事件
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class SMSValidCodeSendSuccessEvent extends BaseEvent {

	/**
	 * 业务场景，如注册、找回密码等
	 */
	private String scene;

	private String mobile;

	private String validCode;

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

}
