package lt.admin.web.contorller.user;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.user.command.ChangeUserCommand;
import lt.user.command.CreateUserCommand;
import lt.user.command.ModifyUserCommand;
import lt.user.command.UpdatePasswordCommand;
import lt.user.entity.User;
import lt.user.qo.UserQO;
import lt.user.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转用户页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/user/view.html";
	}

	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, UserQO qo) {
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = userService.queryPagination(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pagination);
	}

	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, String userId, Model model) {
		User user = null;
		try {
			user = userService.get(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		return "/user/edit.html";
	}

	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request, ModifyUserCommand command) {
		return userService.modifUser(command);
	}

	@RequestMapping("/addView")
	public String addView(HttpServletRequest request) {
		return "/user/add.html";
	}

	@RequestMapping("/add")
	@ResponseBody
	public String add(HttpServletRequest request, CreateUserCommand command)
			throws Exception {
		return userService.createUser(command, request);

	}

	@RequestMapping("/chanageStatus")
	@ResponseBody
	public String change(HttpServletRequest request, ChangeUserCommand command) {
		
		return userService.change(command);
	}

	/**
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/update-password")
	@ResponseBody
	public String add(HttpServletRequest request, UpdatePasswordCommand command) {

		return userService.UserUpdatePassword(command);

	}

	@RequestMapping("/validateLoginName")
	@ResponseBody
	public String validateLoginName(HttpServletRequest request, UserQO qo) {

		int count = userService.queryCount(qo);
		if (count > 0) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/validateMobile")
	@ResponseBody
	public String validateMobile(HttpServletRequest request, UserQO qo) {

		int count = userService.queryCount(qo);
		if (count > 0) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/validateEmail")
	@ResponseBody
	public String validateEmail(HttpServletRequest request, UserQO qo) {

		int count = userService.queryCount(qo);
		if (count > 0) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/validateIdCardNo")
	@ResponseBody
	public String validateIdCardNo(HttpServletRequest request, UserQO qo) {

		int count = userService.queryCount(qo);
		if (count > 0) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/getHeadImage")
	@ResponseBody
	public String getHeadImage(HttpServletRequest request, UserQO qo) {
		User user = null;
		try {
			user = userService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(user);
	}
	
	
	@RequestMapping("/getValidateShop")
	@ResponseBody
	public String getValidateShop(HttpServletRequest request,String userId) {
		User user = null;
		try {
			if(StringUtils.isNotBlank(userId)){
				user = userService.get(userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(user);
	}
	
	
	@RequestMapping("/apply-shop")
	public String applyShop(HttpServletRequest request, UserQO qo,Model model) {
		User user = null;
		try {
			user = userService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		return "/user/applyshop.html";
	}
	
	
	

}
