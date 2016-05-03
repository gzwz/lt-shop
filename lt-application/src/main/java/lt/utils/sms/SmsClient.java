package lt.utils.sms;

import gzlazypack.common.util.SpringContextUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSDK;
/**
 * 短信发送客户端
 * @author chh
 *
 */
public class SmsClient {
	
	public static CCPRestSDK restAPI;
	
	private static SmsConfig smsConfig;
	static{
		restAPI = new CCPRestSDK();
		if (null == smsConfig) {
			smsConfig = (SmsConfig) SpringContextUtil.getBean("smsConfig");
		}
		restAPI.init(smsConfig.getUrl(), smsConfig.getPort());// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(smsConfig.getAccountSid(), smsConfig.getAuthToken());// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(smsConfig.getAppId());// 初始化应用ID
	}
	
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static boolean sendSMS(String to,String code) {
		 return sendSMS(to, "62332", new String[]{ code,  String.valueOf(1)});
	}
	
	
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private static boolean sendSMS(String to, String templateId, String[] datas) {
		HashMap<String, Object> result = null;
		result = restAPI.sendTemplateSMS(to,templateId, datas);
		if (null != result && "000000".equals(result.get("statusCode"))) {
			return true;
		}
		return false;
	}
	
}
