package lt.admin.web.contorller.index;


import gzlazypack.common.util.PwdUtil;
import gzlazypack.common.util.ResultJSON;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.admin.service.AdminService;
import lt.admin.web.contorller.BaseController;
import lt.admin.web.contorller.sys.AdminController;
import lt.utils.SessionUtil;

/**
 * 登录
 * @author wxp
 *
 */
@Controller
public class LoginController extends BaseController{

	/** 日志管理 */
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/** 管理员service */
	@Autowired
	private AdminService adminService;
	
	/**
	 * 跳转登陆页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		return "/admin/login/login.html";
	}
	
	
	/**
	 * 登陆提交
	 * @param request
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping("/submit/login")
	@ResponseBody
	public String submitLogin(HttpServletRequest request, String loginName, String password){
		
		try {
			adminService.login(request, loginName, password);
		} catch (Exception e) {
			logger.error(
					"【管理员登录异常】LoginController.checkLogin(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, e.getMessage());
		}
		
		return ResultJSON.resultToJSONStr(true, "管理员登录成功");
	}
	
	
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionUtil.removeSession(request);
        response.sendRedirect(request.getContextPath()+"/login");
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(PwdUtil.getPwd("000000"));
	}
}
