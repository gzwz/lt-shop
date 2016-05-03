package lt.sitemsg.exception;

import gzlazypack.common.component.BaseException;

@SuppressWarnings("serial")
public class MsgException extends BaseException {
	
	public final static String USER_NULL = "user_null";	//	用户不能为空
	
	public MsgException(String code, String message) {
		super(code, message);
	}
	
}
