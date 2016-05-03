package lt.base.event;

import gzlazypack.common.component.BaseEvent;

/**
 * 
 * @类功能说明：用户验证码校验成功事件
 * @作者：yuxx
 * @创建时间：2015年1月23日上午10:11:05
 * 
 */
@SuppressWarnings("serial")
public class SMSValidCodeCorrectEvent extends BaseEvent {

	private String mobile;

	public SMSValidCodeCorrectEvent(String describe) {
		super(describe);
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
