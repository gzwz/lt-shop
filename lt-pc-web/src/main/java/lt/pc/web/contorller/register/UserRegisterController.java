package lt.pc.web.contorller.register;


import gzlazypack.common.util.ResultJSON;
import gzlazypack.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.base.command.SendSMSValidCodeCommand;
import lt.base.entity.SMSValidateSaga;
import lt.base.exception.SagaException;
import lt.base.service.SMSValidateSagaService;
import lt.pc.web.contorller.BaseController;
import lt.user.command.UserRegisterCommand;
import lt.user.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/register")
public class UserRegisterController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SMSValidateSagaService smsService;

	/*
	 * 初始化第一个页面
	 */
	@RequestMapping(value = "")
	public String viewInit(HttpServletRequest request){
		return "register/register.html";
	}
	/**
	 * 	
	 * @param 用户注册
	 * @return
	 * @throws UserException 
	 * @throws SagaException 
	 */
	@ResponseBody
	@RequestMapping(value = "/insert")
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
		System.out.println("tel=="+mobile+"   type =="+type+"       "+ StringUtil.checkMobile(mobile));
		
		if (!StringUtils.isNotBlank(mobile)) {
			return ResultJSON.resultToJSONStr(false, "手机号不能为空");
		}else if (type != 1) {
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
