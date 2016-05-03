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
public class SMSValidateResultUsedEvent extends BaseEvent {

	private String sagaId;

	public SMSValidateResultUsedEvent(String describe) {
		super(describe);
	}

	public String getSagaId() {
		return sagaId;
	}

	public void setSagaId(String sagaId) {
		this.sagaId = sagaId;
	}

}
