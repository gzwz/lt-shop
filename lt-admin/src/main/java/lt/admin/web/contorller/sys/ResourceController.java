package lt.admin.web.contorller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.admin.command.CreateResourceCommand;
import lt.admin.command.ModifyResourceCommand;
import lt.admin.dto.ResourcesDTO;
import lt.admin.entity.Resource;
import lt.admin.entity.Role;
import lt.admin.qo.ResourceQO;
import lt.admin.qo.RoleQO;
import lt.admin.service.ResourceService;
import lt.admin.service.RoleService;
import lt.admin.web.contorller.BaseController;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;








import com.alibaba.fastjson.JSON;


/**
 * 资源控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/permission/resource")
public class ResourceController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(ResourceController.class);

	/** 资源service */
	@Autowired
	private ResourceService resourceService;
	/** 角色service */
	@Autowired
	private RoleService roleService;

	/**
	 * 资源新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateResourceCommand command) {

		try {
			resourceService.createResource(command);
		} catch (Exception e) {
			logger.error(
					"【资源新增异常】ResourceController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "资源新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "资源新增成功");
	}
	
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String add(HttpServletRequest request, Model model, String id, Integer type){
		
		ResourceQO qo =new ResourceQO();
		qo.setFetchResource(true);
		List<Resource> resourceList=resourceService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		List<Resource> hasPermList = new  ArrayList<Resource>();
		List<Role> roleList = null;
		if (StringUtils.isNotBlank(id)) {
			
			RoleQO roleQO = new RoleQO();
			roleQO.setFetchResources(true);
			roleQO.setId(id);
			
			roleList= roleService.queryList(roleQO);
			
			for(Role role:roleList){
				hasPermList.addAll(role.getResources());
				
			}
			
		}

		for (Resource authPerm : resourceList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getName());
			map.put("parent", parentId);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("opened", true);

			if (StringUtils.isNotBlank(id)) {
				for (Resource ap : hasPermList) {
					if (authPerm.getId().equals(ap.getId())) {
						map2.put("selected", true);
						break;
					}
				}
			}

			map.put("state", map2);
			Random random = new Random();
			int num = random.nextInt(5);
			if (num % 5 == 0) {
				map.put("icon", "fa fa-file icon-state-default");
			} else if (num % 5 == 1) {
				map.put("icon", "fa fa-file icon-state-success");
			} else if (num % 5 == 2) {
				map.put("icon", "fa fa-file icon-state-warning");
			} else if (num % 5 == 3) {
				map.put("icon", "fa fa-file icon-state-danger");
			} else if (num % 5 == 4) {
				map.put("icon", "fa fa-file icon-state-info");
			}

			strList.add(map);
		}

		return JSON.toJSONString(strList);
	}

	
	
	
	@RequestMapping(value="/getResourceData")
	@ResponseBody
	public String getResourceData(HttpServletRequest request, Model model, String id){
		
		ResourceQO qo =new ResourceQO();
		qo.setFetchResource(true);
		List<Resource> resourceList=resourceService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		List<Resource> hasPermList = new  ArrayList<Resource>();
		List<Role> roleList = null;
		if (StringUtils.isNotBlank(id)) {
			
			RoleQO roleQO = new RoleQO();
			roleQO.setFetchResources(true);
			roleQO.setId(id);
			
			roleList= roleService.queryList(roleQO);
			
			for(Role role:roleList){
				hasPermList.addAll(role.getResources());
				
			}
			
		}

		for (Resource authPerm : resourceList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "0";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}

			map.put("id", authPerm.getId());
			map.put("name", authPerm.getName());
			map.put("pId", parentId);
			map.put("open", true);
			
			if (StringUtils.isNotBlank(id)) {
				for (Resource ap : hasPermList) {
					if (authPerm.getId().equals(ap.getId())) {
						map.put("checked", true);
						break;
					}
				}
			}
			
			Random random = new Random();
			int num = random.nextInt(5);
			if (num % 5 == 0) {
				map.put("icon", "../../resources/ztree/css/zTreeStyle/img/diy/2.png");
			} else if (num % 5 == 1) {
				map.put("icon", "../../resources/ztree/css/zTreeStyle/img/diy/3.png");
			} else if (num % 5 == 2) {
				map.put("icon", "../../resources/ztree/css/zTreeStyle/img/diy/5.png");
			} else if (num % 5 == 3) {
				map.put("icon", "../../resources/ztree/css/zTreeStyle/img/diy/7.png");
			} else if (num % 5 == 4) {
				map.put("icon", "../../resources/ztree/css/zTreeStyle/img/diy/8.png");
			}
			strList.add(map);
		}

		return JSON.toJSONString(strList);
	}
	
	

	/**
	 * 资源修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			ModifyResourceCommand command) {

		try {
			resourceService.modifyResource(command);
		} catch (Exception e) {
			logger.error(
					"【资源修改异常】ResourceController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "资源修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "资源修改成功");
	}

	/**
	 * 资源删除
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
				resourceService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【资源修改异常】ResourceController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "资源删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "资源删除成功");
	}


	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model, ResourceQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("resourceList", ResourcesDTO.domainToDTO((List<Resource>) resourceService.queryList(qo)));
		return "/admin/resource/view.html";
	}

	/**
	 * 跳转资源类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request, ResourceQO qo) {
		Resource pc = null;
		try {
			qo.setFetchResource(true);
			pc = resourceService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【资源详情查询异常】ResourceController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(pc);
	}

}
