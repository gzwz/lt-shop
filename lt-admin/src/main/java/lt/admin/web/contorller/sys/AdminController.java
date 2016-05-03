package lt.admin.web.contorller.sys;


import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.admin.command.CreateAdminCommand;
import lt.admin.command.ModifyAdminCommand;
import lt.admin.converter.AdminConverter;
import lt.admin.dto.AdminDTO;
import lt.admin.entity.Admin;
import lt.admin.entity.AuthAccount;
import lt.admin.entity.Role;
import lt.admin.qo.AdminQO;
import lt.admin.qo.AuthAccountQO;
import lt.admin.qo.RoleQO;
import lt.admin.service.AdminService;
import lt.admin.service.AuthAccountService;
import lt.admin.service.RoleService;
import lt.admin.web.contorller.BaseController;

/**
 * 管理员控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/permission/admin")
public class AdminController extends BaseController{
	
	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	/** 管理员service */
	@Autowired
	private AdminService adminService;
	
	/** 角色service */
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthAccountService accountServiceImpl;

	/**
	 * 管理员新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateAdminCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getRoleIdsChecked().split(",").length;i++){
				list.add(command.getRoleIdsChecked().split(",")[i]);
			}
			if(list.size()>0){
				command.setRoleIds(list);
			}
			adminService.createAdmin(command);
		} catch (Exception e) {
			logger.error(
					"【管理员新增异常】AdminController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "管理员新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "管理员新增成功");
	}

	/**
	 * 管理员修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			ModifyAdminCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getRoleIdsChecked().split(",").length;i++){
				list.add(command.getRoleIdsChecked().split(",")[i]);
			}
			if(list.size()>0){
				command.setRoleIds(list);
			}
			adminService.modifyAdmin(command);
		} catch (Exception e) {
			logger.error(
					"【管理员修改异常】AdminController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "管理员修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "管理员修改成功");
	}

	/**
	 * 管理员删除
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			for(int i=0;i<id.split(",").length;i++){
				AdminQO qo=new AdminQO();
				qo.setId(id.split(",")[i]);
				Admin admin=adminService.queryUnique(qo);
				if(null!=admin){
					accountServiceImpl.deleteById(admin.getAuthAccountId());
				}
				adminService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【管理员修改异常】AdminController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "管理员删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "管理员删除成功");
	}

	/**
	 * 管理员列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, AdminQO qo) {

		Pagination pagination = new Pagination();

		try {
			pagination.setPageNo(qo.getPageNo());
			qo.setNameLike(true);
			qo.setFetchRoles(false);
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = adminService.queryPagination(pagination);
			List<AdminDTO> adminDTOList = new ArrayList<>();
			for (Object obj : pagination.getList()) {
				Admin admin = (Admin) obj;
				AdminDTO adminDTO = new AdminDTO();
				AuthAccountQO aaQo = new AuthAccountQO();
				aaQo.setDomainId(admin.getId());
				AuthAccount authAccount= accountServiceImpl.queryUnique(aaQo);
				adminDTO = AdminConverter.domainToDTO(admin, authAccount);
				adminDTOList.add(adminDTO);
			}
			pagination.setList(adminDTOList);
		} catch (Exception e) {
			logger.error("【管理员列表查询异常】AdminController.query(request, qo)"
					+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}

	/**
	 * 跳转管理员类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model) {
		RoleQO qo=new RoleQO();
		qo.setFetchResources(false);
		List<Role> list=roleService.queryList(qo);
		model.addAttribute("roleList", list);
		return "/admin/view.html";
	}
	
	/**
	 *修改密码页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/password_page")
	public String password_page(HttpServletRequest request,Model model) {
		return "/admin/update_password.html";
	}

	/**
	 * 管理员类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request, AdminQO qo) {
		AdminDTO dto=null;
		try {
			qo.setFetchRoles(true);
			qo.setBatchResult(true);
			qo.setFetchAuthAccount(true);
			Admin pc  = adminService.queryUnique(qo);
			if(null!=pc){
				dto=new AdminDTO();
				dto.setId(pc.getId());
				dto.setRoles(pc.getRoles());
				dto.setName(pc.getName());
				dto.setTelephone(pc.getTelephone());
				dto.setLastLoginDate(pc.getLastLoginDate());
				AuthAccount authAccount=accountServiceImpl.get(pc.getAuthAccountId());
				dto.setLoginName(authAccount.getLoginName());
				dto.setEncryptPassword(authAccount.getEncryptPassword());
			}
			
		} catch (Exception e) {
			logger.error("【管理员详情查询异常】AdminController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(dto);
	}
}
