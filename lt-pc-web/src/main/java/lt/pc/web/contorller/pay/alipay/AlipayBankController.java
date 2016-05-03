package lt.pc.web.contorller.pay.alipay;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.pay.alipay.entity.AlipayBank;
import lt.pay.alipay.service.AlipayBankService;
import lt.pay.alipay.util.AlipayNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("pay/alipay_bank")
public class AlipayBankController {
	
	@Autowired
	private AlipayBankService alipayService;
	
	@RequestMapping()
	public String view(){
		return "pay/alipayBank/payTest.html";
	}

	@ResponseBody
	@RequestMapping("/bank")
	public void payUtilUrl(AlipayBank payInfo, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			alipayService.WebPayBank(payInfo, response);
			System.out.println("这是网银支付请亲");
		} catch (Exception e) {
			System.out.println(e.toString()+"这是网银支付请亲");
		//	Log4j.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取支付宝及时返回POST过来反馈信息
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/return")
	public String getReturnInfo(HttpServletRequest request,HttpServletResponse response,String subject) throws IOException{
 
		//获取支付宝POST过来反馈信息
		
		System.out.println("alipay_bank return**************");
	
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf8");
			params.put(name, valueStr);
		}
		System.out.println(params.get("trade_status"));
		if (AlipayNotify.verify(params)) {
			 
			
		//	response.getWriter().print("success");
		}
		return "pay/payOk.html";
	}
	/**
	 * 获取支付宝支付成功后的异步通知
	 * 确认依据支付成功
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/notifyReturn")
	public String notifyRetun(HttpServletRequest request,String out_trade_no,String buyer_email){
		//获取支付宝POST过来反馈信息
		System.out.println("网银支付 异步 notifyReturn");
		
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//	valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf8");
			params.put(name, valueStr);
		}
		System.out.println(params.get("trade_status")+" body="+params.get("body")+"网银 异步 此时并没有验证数据合法性");
		if (AlipayNotify.verify(params)) {
			switch (params.get("trade_status").toString()) {

			case "WAIT_BUYER_PAY":  // 交易创建，等待买家付款。
				
				System.out.println("WAIT_BUYER_PAY***********");
				break;
			case "TRADE_CLOSED":  // 在指定时间段内未支付时关闭的交易； 在交易完成全额退款成功时关闭的交易。
				System.out.println("TRADE_CLOSED**********");
				break;
			case "TRADE_SUCCESS":  // 交易成功，且可对该交易做操作，如：多级分润、退款等。
				
				System.out.println("恭喜你，网银支付成功，并且收到异步支付的通知了"+new Date().getTime());
				
				System.out.println("TRADE_SUCCESS***********");
				break ;
			case "TRADE_PENDING":  // 等待卖家收款（买家付款后，如果卖家账号被冻结）。
				System.out.println("TRADE_PENDING**************");
				break;
			case "TRADE_FINISHED":  // 交易成功且结束，即不可再做任何操作。
				System.out.println("TRADE_FINISHED**************");
				break;
				
			default:
				System.out.println("-----------------------");
			//	response.getWriter().print("aaaaaaaaaa");
				break;
				
			}
			System.out.println("----------+++++++++++++++++++++++-------------");
		//	response.getWriter().print("bbbbbbbbbb");
		 return "success";
		} 
		return "fail";
	}
	
	
}
