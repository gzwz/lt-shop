package lt.base.exception;

import gzlazypack.common.component.BaseException;

/**
 * 流程处理异常
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class SagaException extends BaseException {
	
	public final static String SAGA_NOT_EXIST = "saga_not_exist";
	
	/**
	 * 细节备注
	 */
	private String detail;

	public SagaException() {
		super();
	}

	public SagaException(Integer code, String message) {
		super(code, message);
	}

	public SagaException(String code, String message) {
		super(code, message);
	}

	public final static Integer SAGA_NOT_EXISTS = 0; // 流程不存在
	public final static Integer EVENT_NOT_BELONG_THIS_SAGA = 1; // 所发生的事件不属于该流程
	public final static Integer VALIDCODE_PAST_DUE = 2; // 验证码已过期
	public final static Integer VALIDCODE_TOO_MANY_TIMES = 3; // 验证次数过多
	public final static Integer VALIDCODE_WRONG = 4; // 验证码错误
	public final static Integer SAGA_NOT_SUCCESS = 5; // 流程还未成功

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
