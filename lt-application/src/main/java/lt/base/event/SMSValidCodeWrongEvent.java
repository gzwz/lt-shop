package lt.base.event;

import gzlazypack.common.component.BaseEvent;

/**
 * 
 * @类功能说明：用户验证码输错事件
 * @作者：yuxx
 * @创建时间：2015年1月23日上午10:11:05
 * 
 */
@SuppressWarnings("serial")
public class SMSValidCodeWrongEvent extends BaseEvent {

	private String mobile;

	public SMSValidCodeWrongEvent(String describe) {
		super(describe);
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
