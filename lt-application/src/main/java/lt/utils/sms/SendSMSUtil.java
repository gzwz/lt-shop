package lt.utils.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lt.base.command.SendSMSValidCodeCommand;
import lt.base.entity.SMSBean;
import lt.base.entity.SendResultBean;

/**
 * 短信发送实现类 - 调用短信发送平台的接口
 * @author l.wz
 *
 */
public class SendSMSUtil {
	
	/**
	 * 单个短信发送
	 * @param SMSBean smsBean 短信对象【接收号码、短信内容、发送日期】
	 * @throws IOException
	 * return SMSSendStatistics 【发送结果：发送对象、发送详情对象集】
	 * 
	 */
	public static synchronized SendResultBean sendMessage(SendSMSValidCodeCommand command) throws IOException {

		HttpURLConnection connection = null;
		InputStream is=null;
		try {

			// 声明 短信发送结果实体 句柄[用于封装短信发送结果]
			SendResultBean resultBean = null;
			// 定义接收短信平台返回的发送结果信息字符串
			String resultStr = null;

			// 封装请求信息并返回字符串、包括请求地址和请求参数
			String requestStr = getRequestMeg(command);
			// 创建URL对象
			URL url = new URL(requestStr);
			// 打开url连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod(Constant.REQUEST_METHOD);
			// 发送
			  is = url.openStream();

			// 转换返回值
			resultStr = SendSMSUtil.convertStreamToString(is);

			// 本条短信发送结束、开始收集发送结果
			resultBean = new SendResultBean();
			// 本条短信的接收电话号码
			resultBean.setTelNo(command.getMobile());
			// 短信内容
			resultBean.setContent(command.getContent());
			// 发送日期
			resultBean.setSendDate(convertDateToStr(command.getSendDate()));
			// 封装发送结果【成功、失败及失败原因等信息】
			resultBean = sendStatusUtil(resultStr, resultBean);
			return resultBean;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (is != null) {
				is.close();
			}
		}

	}
	
	
	/**
	 * 接口回馈信息说明：
	 * 短信提交响应为英文逗号隔开的一行数据，
	 * 无论发送的号码是多少，一个发送请求只返回一个sendid，
	 * 如果响应的状态不是“0”，则只有状态和消息。
	 * 
	 * code,sendid,invalidcount,successcount,blackcount,msg
	 * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息
	 * 
	 * @author l.yang
	 * @since 2015-11-19
	 * 拼接短信发送  请求信息 字符串
	 */
	public static String getRequestMeg(SendSMSValidCodeCommand command) throws UnsupportedEncodingException{
		
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer(Constant.URL_VALUE);

		// 向StringBuffer追加用户名
		sb.append("name="+Constant.NAME_VALUE);

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd="+Constant.PWD_VALUE);

		// 向StringBuffer追加手机号码
		sb.append("&mobile="+command.getMobile());

		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+URLEncoder.encode(command.getContent(),Constant.UTF_8));
		
		//追加发送时间，可为空，为空为及时发送
		sb.append("&stime="+convertSendDate(command.getSendDate()));
		
		//加签名
		sb.append("&sign="+URLEncoder.encode(Constant.SIGN_VALUE,Constant.UTF_8));
		
		//type为固定值pt  extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno="+Constant.EXTNO_VALUE);
		// 创建url对象
		//String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		System.out.println("请求信息:"+sb.toString());
		
		return sb.toString();
	}
	
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, Constant.UTF_8);  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	
	/**
	 * 获取日期、格式：yyyy-mm-dd 日期转换
	 * @param date
	 * @return
	 */
	public static String convertDateToStr(String date){
		String dateStr = "";
		if(null!=date && !"".equals(date)){
			dateStr = date;
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
			dateStr = sdf.format(new Date()); 
		}
		
		return dateStr;
	}
	
	
	/**
	 * 判断并封装发送状态
	 * 		处理平台反馈的信息
	 * @param sendResultStr
	 * @param resultBean
	 * @return
	 */
	public static SendResultBean sendStatusUtil(String sendResultStr,SendResultBean resultBean){
		if(null == sendResultStr || "".equals(sendResultStr.trim())){
			// 状态未知
			resultBean.setSendResult(Constant.UNKNOWN);
			resultBean.setFailureReason(Constant.UNKNOWN);
			resultBean.setOtherDes(sendResultStr);
			
			// 平台反馈信息为空，直接返回
			return resultBean;
		}
		// 获取状态码[逗号分割]
		String[] resultInfo = sendResultStr.split(Constant.SPLIT_IDF);
		String code = resultInfo[0].trim();
		// 如果状态码为0、表示发送成功
		if(Constant.CODE_0.equals(code)){
			// 发送结果【成功】
			resultBean.setSendResult(Constant.SUCCESS);
			// 描述信息
			resultBean.setOtherDes(sendResultStr);
		}else {
			// 发送结果【失败】
			resultBean.setSendResult(Constant.FAILURE);
			// 如果失败、封装失败原因  ,其它描述信息
			if(Constant.CODE_01.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_01_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_1.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_1_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_2.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_2_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_3.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_3_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_4.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_4_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_10.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_10_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_11.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_11_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_12.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_12_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_13.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_13_STR);
				resultBean.setOtherDes(sendResultStr);
			}else if(Constant.CODE_14.equals(code)){
				
				resultBean.setFailureReason(Constant.CODE_14_STR);
				resultBean.setOtherDes(sendResultStr);
			} else {

				resultBean.setFailureReason(Constant.UNKNOWN);
				resultBean.setOtherDes(sendResultStr);
			}
		}
		return resultBean;
	}
	
	/**
	 * 获取日期、格式：yyyy-mm-dd 日期转换
	 * @param date
	 * @return
	 */
	public static String convertSendDate(String date){
		String dateStr = "";
		if(null != date && !"".equals(date)){
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
			try {
				// 如果不能使用yyyy-MM-dd格式解析，则不能使用该日期
				sdf.parse(date);
				dateStr = date;
			} catch (ParseException e) {
				return "";
			}
		}
		
		return dateStr;
	}
}