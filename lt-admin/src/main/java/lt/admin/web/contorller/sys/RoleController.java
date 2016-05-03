package lt.admin.web.contorller.sys;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.command.CreateRoleCommand;
import lt.admin.command.ModifyRoleCommand;
import lt.admin.entity.Role;
import lt.admin.qo.RoleQO;
import lt.admin.service.RoleService;
import lt.admin.web.contorller.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/permission/role")
public class RoleController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(RoleController.class);

	/** 角色service */
	@Autowired
	private RoleService roleService;

	/**
	 * 角色新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request, CreateRoleCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getIds().split(",").length;i++){
				list.add(command.getIds().split(",")[i]);
			}
			if(list.size()>0){
				command.setResourceIds(list);
			}
			roleService.createRole(command);
		} catch (Exception e) {
			logger.error(
					"【角色新增异常】RoleController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "角色新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "角色新增成功");
	}

	/**
	 * 角色修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request, ModifyRoleCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getIds().split(",").length;i++){
				list.add(command.getIds().split(",")[i]);
			}
			if(list.size()>0){
				command.setResourceIds(list);
			}
			roleService.modifyRole(command);
		} catch (Exception e) {
			logger.error(
					"【角色修改异常】RoleController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "角色修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "角色修改成功");
	}

	/**
	 * 角色删除
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
				roleService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【角色修改异常】RoleController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "角色删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "角色删除成功");
	}

	/**
	 * 角色列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, RoleQO qo) {

		Pagination pagination = new Pagination();

		try {
			pagination.setPageNo(qo.getPageNo());
			qo.setFetchResources(false);
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = roleService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error(
					"【角色列表查询异常】RoleController.query(request, qo)"
							+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}

	/**
	 * 跳转角色类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/admin/role/view.html";
	}
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/admin/role/add.html";
	}
	
	


	
	@RequestMapping("/resourseInfo")
	public String add(HttpServletRequest request,String resourses,Model model) {
		model.addAttribute("resourses", resourses);
		return "/admin/role/resourceInfo.html";
	}
	
	/**
	 * 跳转角色类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, RoleQO qo,Model model) {
		Role pc = null;
		try {
			qo.setFetchResources(true);
			qo.setBatchResult(true);
			pc = roleService.queryUnique(qo);
		} catch (Exception e) {
			logger.error(
					"【角色详情查询异常】RoleController.edit(request, qo)"
							+ e.getMessage(), e);
		}
		
		model.addAttribute("role", pc);
		return "/admin/role/edit.html";
	}
}
