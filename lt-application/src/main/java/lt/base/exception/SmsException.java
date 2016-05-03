package lt.base.exception;

import gzlazypack.common.component.BaseException;

/**
 * 短信异常
 *
 */
@SuppressWarnings("serial")
public class SmsException extends BaseException {

	
	public SmsException() {
				
	}

	public SmsException(Integer code, String message) {
		super(code, message);
	}
	
	/**
	 * 手机号错误
	 */
	public final static Integer MOBILE_WRONG=4;
	/**
	 * 未找到对应id的模板
	 */
	public final static Integer NOT_FOUND_TEMPLEATE = 5; 

	/**
	 * 添加模板失败
	 */
	public final static Integer ADD_TEMPLEATE_FAIL = 6; 
	
	/**
	 * 模板不可用
	 */
	public final static Integer TEMPLEATE_ERROR = 7;
	
	/**
	 *  同一手机号30秒内重复提交相同的内容
	 */
	public final static Integer SAME_MOBILE_SAME_IN_30_SECOND = 8; 
	
	/**
	 * 同一手机号5分钟内重复提交相同的内容超过3次
	 */
	public final static Integer SAME_CONTENT = 9; 
	
	/**
	 * 手机号黑名单过滤
	 */
	public final static Integer MOBILE_BLACKlIST = 10;  
	
	/**
	 * 解码失败	请确认内容编码是否设置正确
	 */
	public final static Integer ENCODE_ERROR = 14; 
	
	/**
	 * 签名不匹配	短信签名与预设的固定签名不匹配
	 */
	public final static Integer SIGN_UNMATCH = 15; 
	
	/**
	 * 签名格式不正确	短信内容不能包含多个签名【 】符号
	 */
	public final static Integer SING_ERROR = 16;
	
	/**
	 * 24小时内同一手机号发送次数超过限制
	 */
	public final static Integer TIMES_LIMIT = 17; 
	
	/**
	 * IP没有权限
	 */
	public final static Integer IP_LIMIT = -3; 
	
	/**
	 * 访问次数超限
	 */
	public final static Integer VISIT_TIME_TOO_MONY = -4; // 旧密码有误
	
	/**
	 * 访问频率超限
	 */
	public final static Integer VISIT_TOO_FAST = -5; // 用户不存在

	/**
	 * 未知异常
	 */
	public final static Integer UNKNOWN_EXCEPTION = -50;
	
	/**
	 * 系统繁忙
	 */
	public final static Integer SYSTEM_BUSY = -51;
	
	/**
	 * 用户开通过固定签名功能，但签名未设置
	 */
	public final static Integer SIGN_NO_SETTING = -57;

}
