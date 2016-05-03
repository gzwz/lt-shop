package lt.admin.web.contorller;

import gzlazypack.common.util.DateUtil;

import javax.servlet.http.HttpServletRequest;

import lt.pay.utils.RandomUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 * @author 
 *
 */
@Controller
public class Test {

	/**
	 * 跳转登陆页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/test")
	public String login(HttpServletRequest request){
		return "/exmple/edit.html";
	}
	
	@org.junit.Test
	public void order(){
	System.out.println(DateUtil.getDate());
	System.out.println(DateUtil.getCurMilli());
	
	for (int i=0; i < 10; i++) {
		System.out.println(DateUtil.getDate()+RandomUtil.numberString(3));
	}
		
	
	
	
	}
	
	
	
	
	
	
	
	
}
