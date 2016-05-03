package lt.utils.sms;
/**
 * 常量定义接口
 * @author Administrator
 *
 */
public interface Constant {
	
	/**
	 * 短信平台请求参数
	 */
	// 短信平台URL
	public static final String URL_VALUE = "http://web.cr6868.com/asmx/smsservice.aspx?";
	// 短信平台用户名
	public static final String NAME_VALUE = "15585249888"; 
	// 短信平台用户密码
	public static final String PWD_VALUE = "925D8098A209EFBDD9C418444C3E";
	// 签名
	public static final String SIGN_VALUE = "卡车网";
	// 填写pt[指定值、不可更改]
	public static final String TYPE_VALUE = "pt";
	// 扩展码【可选、自定义】
	public static final String EXTNO_VALUE = "";
	// 请求提交方式 GET 或 POST
	public static final String REQUEST_METHOD = "POST";
	// 编码格式 UTF-8
	public static final String UTF_8 = "UTF-8";
	// 自定义日期格式Str
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	// 日期时间格式
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH时mm分ss秒";
	
	/*
	 * 短信发送后、平台返回的信息值【状态码code】
	 */
	// 成功、失败 
	public static final String SUCCESS = "成功";
	public static final String FAILURE = "失败";
	public static final String UNKNOWN = "未知";
	//	0	提交成功
	public static final String CODE_0 = "0";
	//	1	含有敏感词汇
	public static final String CODE_1 = "1";
	public static final String CODE_1_STR = "含有敏感词汇";
	//	2	余额不足
	public static final String CODE_2 = "2";
	public static final String CODE_2_STR = "余额不足";
	//	3	没有号码
	public static final String CODE_3 = "3";
	public static final String CODE_3_STR = "没有号码";
	//	4	包含sql语句
	public static final String CODE_4 = "4";
	public static final String CODE_4_STR = "包含sql语句";
	//	10	账号不存在
	public static final String CODE_10 = "10";
	public static final String CODE_10_STR = "账号不存在";
	//	11	账号注销
	public static final String CODE_11 = "11";
	public static final String CODE_11_STR = "账号注销";
	//	12	账号停用
	public static final String CODE_12 = "12";
	public static final String CODE_12_STR = "账号停用";
	//	13	IP鉴权失败
	public static final String CODE_13 = "13";
	public static final String CODE_13_STR = "IP鉴权失败";
	//	14	格式错误
	public static final String CODE_14 = "14";
	public static final String CODE_14_STR = "格式错误";
	//	-1	系统异常
	public static final String CODE_01 = "-1";
	public static final String CODE_01_STR = "系统异常";
	
	// 短信平台反馈信息split分割符号
	public static final String SPLIT_IDF = ",";
	// 文件名称最后一个圆点【后缀名圆点】
	public static final String EXT_POINT = ".";
	
	// 短信列表上传目录【目录不存在时要创建、目录下可能存在多个文件】
	public static final String SMS_FILE_COMMITTED_PATH = "C:\\短信发送文件\\待发送\\";
	// 短信发送报告目录
	public static final String SMS_SEND_REPORT_PATH = "C:\\短信发送文件\\发送报告\\";
	
	// 表格文件后缀名
	public static final String EXCEL_EXT_XLS = "xls";
	public static final String EXCEL_EXT_XLSX = "xlsx";
	// 备份文件后缀名【自定义】
	public static final String EXT_NAME_BAK = ".bak";
	
	// 发送报告摘要：【sheet名】
	public static final String REPORT_SIMPLE = "发送报告概要";
	// 发送报告明细【sheet名】
	public static final String REPORT_DETAIL = "发送报告明细";
	
	// 作业调度间隔时间【单位：毫秒】
	public static final long JOB_INTERVAL_TIME = 7200000;
}
