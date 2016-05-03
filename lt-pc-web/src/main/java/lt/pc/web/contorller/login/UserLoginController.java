package lt.pc.web.contorller.login;


import gzlazypack.common.util.ResultJSON;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.pc.web.contorller.BaseController;
import lt.user.service.UserService;
import lt.utils.SessionUtil;

 
@Controller
@RequestMapping(value="/login")
public class UserLoginController extends BaseController {
	
	
	/** 普通会员登陆service */
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String login(HttpServletRequest request,String redirect_url){
		return "/login/login.html";
	}
	@RequestMapping(value="/check")
	@ResponseBody
	public String check(String userNamne, String pwd,HttpServletRequest request,HttpServletResponse response){
		
		return userService.login(request, userNamne, pwd);
	}
	
	@RequestMapping("/forget-password")
	public String forgetPwd(){
		
		return "/forget/forgetpwd.html";
	}
	
	@RequestMapping(value="/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response,String url) throws IOException{
		try {
			SessionUtil.removeUserSession(request);
		} catch (Exception e) {
			
		  return ResultJSON.resultToJSONStr(false, "退出失败");
		}
		return ResultJSON.resultToJSONStr(true, "退出成功");
	}
	
	
	
}
