package lt.adnroid.contorller.login;


import gzlazypack.common.util.ResultJSON;
import gzlazypack.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.adnroid.contorller.BaseController;
import lt.base.command.SendSMSValidCodeCommand;
import lt.base.entity.SMSValidateSaga;
import lt.base.exception.SagaException;
import lt.base.service.SMSValidateSagaService;
import lt.user.command.UserRegisterCommand;
import lt.user.dto.UserDTO;
import lt.user.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


 
 
@Controller
@RequestMapping(value="/android/")
public class AndroidUserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SMSValidateSagaService smsService;

	
	/** 日志管理 */
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(AndroidUserController.class);
	
	/** 普通会员登陆service */

	
	
	@ResponseBody
	@RequestMapping(value="/login")
	public String check(String userName,String pwd,HttpServletRequest request ){
		if (!StringUtils.isNotBlank(userName)||!StringUtils.isNotBlank(pwd)) {
			return ResultJSON.resultToJSONStr(false, "用户名或者密码不为空");
		}
		UserDTO userDTO=userService.androidLogin(request, userName, pwd);
		if (userDTO!=null) {
			return ResultJSON.resultToJSONStr(true, "登陆成功", userDTO);
		}else {
			return	ResultJSON.resultToJSONStr(false, "用户名或者密码错误");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/register")
	public String register(HttpServletRequest request,HttpServletResponse response,UserRegisterCommand userComm) throws SagaException, UserException{
		return userService.registerUser(userComm, request);
	} 
	/**
	 * 检查用户名或者手机号 是否已经注册过
	 */
	@RequestMapping(value = "/checkUserName")
	@ResponseBody
	public String checkParam(HttpServletRequest request,String loginName) {
		return userService.checkParam(loginName,"loginName");
	}  
	/**
	 * 获取短信验证码
	 * 0 表示失败
	 */
	@ResponseBody
	@RequestMapping(value="/getSMS")
	public String getSMS(HttpServletRequest request,String mobile,Short type){
		System.out.println("tel=="+mobile+"   type =="+type);
		if (!StringUtils.isNotBlank(mobile)) {
			return ResultJSON.resultToJSONStr(false, "手机号不能为空");
		}else if (!StringUtil.checkMobile(mobile)) {
			return ResultJSON.resultToJSONStr(false, "手机号格式错误");
		} else if (type != 1) {
			return ResultJSON.resultToJSONStr(false, "非注册流程");
		}
		
		SendSMSValidCodeCommand command = new SendSMSValidCodeCommand();
		command.setMobile(mobile);
		command.setType(type);
		try {
		//	SMSValidateSaga saga =  (SMSValidateSaga) smsService.sendSMS(command);
			SMSValidateSaga saga = smsService.sendSMS(command);
			if (saga != null) {
				//接收到 发送短信的id 表示成功
				return ResultJSON.resultToJSONStr(true, saga.getId());
			}
		} catch (SagaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultJSON.resultToJSONStr(false, "失败");
	}
	
	
	
}
