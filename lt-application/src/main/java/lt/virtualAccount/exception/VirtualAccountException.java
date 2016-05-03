package lt.virtualAccount.exception;

import gzlazypack.common.component.BaseException;


@SuppressWarnings("serial")
public class VirtualAccountException extends BaseException {

	public VirtualAccountException(int code, String message) {
		super(code, message);
	}

	public final static Integer AMOUNT_NOT_NULL = 1001; // 余额不为空
	public final static Integer ACCOUNT_FORBIDDEN = 1002; // 帐户已禁用
	public final static Integer ACCOUNT_CLOSE = 1003; // 帐户已关闭
	public final static Integer AVAIABLE_AMOUNT_NOT_ENOUTH = 1004; // 帐户可用余额不足
	public final static Integer FROZEN_AMOUNT_WRONG = 1005; // 冻结金额有误
	public final static Integer FROZEN_ACCOUNT_NOT_EXIST = 1006; // 冻结帐号不存在
	public final static Integer SMS_VALIDATE_FAIL = 1007; // 短信验证失败
	public final static Integer BOC_ACCOUNT_VALIDATE_FAIL = 1008; // 中行积分接口客户身份验证失败
	public final static Integer BOC_ACCOUNT_COMSUME_FAIL = 1009; // 中行积分接口扣减失败
	public final static Integer BOC_ACCOUNT_QUERY_FAIL = 1010; // 中行积分接口查询失败
	public final static Integer BOC_ACCOUNT_EXIST = 1011; // 中行积分账号已存在
	public final static Integer BOC_ORDER_CANCEL_FAIL = 1012; // 中行积分订单取消失败
	public final static Integer ACCOUNT_NOT_EXIST = 1013; // 虚拟账户不存在
	public final static Integer CURRENCY_NOT_EXIST = 1014; // 虚拟货币不存在
	public final static Integer ACCOUNT_DUPLICATED = 1015; // 虚拟账户重复
	public final static Integer APPLICATION_NOT_EXIST = 1016; // 申领不存在
	public final static Integer APPLICATION_STATUS_ERROR = 1017; // 申领状态错误
	public final static Integer TRANS_RECORD_NOT_EXIST = 1018; // 交易记录不存在
	
	public final static Integer INTERFACE_INVOCK_ERROR = 2000; // 第三方接口调用失败
	public final static Integer PARAMETER_ERROR = 2001; // 参数缺失或错误
	public final static Integer NO_NOT_APPLIED_CONVENIENCE_CARD = 2002; // 无便民生活服务卡可申请
	public final static Integer PHONE_APPLIED_CONVENIENCE_CARD = 2003; // 该手机号码已申请过便民生活服务卡
}
