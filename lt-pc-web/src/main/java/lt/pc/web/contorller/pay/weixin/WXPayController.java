package lt.pc.web.contorller.pay.weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.pay.wx.common.Configure;
import lt.pay.wx.common.Signature;
import lt.pay.wx.entity.WeiXin;
import lt.pay.wx.service.WeixinService;
import lt.pc.web.contorller.BaseController;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay/wxPay")
public class WXPayController extends BaseController{
	
	@Autowired
	WeixinService wxService;
	

	@RequestMapping("")
	public String view() {
		return "pay/weixin/payTest.html";
	}
	
	
	/**
	 * 测试进入指定jsp页面
	 * 
	 * @return
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/pay")
	public void payUtilUrl(WeiXin weiXin, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
		System.out.println("进入微信支付");
		//签名
		System.out.println(Signature.getSign(weiXin));
	//	Signature.getSign(weiXin);
		
		try {
			
			
		//	alipayService.WebPay(payInfo, response);
		} catch (Exception e) {
			System.out.println("微信支付" + e.toString());
			// Log4j.error(e.toString());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		System.out.println(Configure.getKey());
	}
}
