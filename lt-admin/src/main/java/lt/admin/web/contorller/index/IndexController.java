package lt.admin.web.contorller.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.admin.dto.AdminDTO;
import lt.admin.entity.Admin;
import lt.admin.entity.Resource;
import lt.admin.entity.Role;
import lt.admin.qo.AdminQO;
import lt.admin.qo.RoleQO;
import lt.admin.service.AdminService;
import lt.admin.service.ResourceService;
import lt.admin.service.RoleService;
import lt.admin.web.contorller.BaseController;
import lt.utils.FileUploader;
import lt.utils.SessionUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;

/**
 * 首页入口，公共页面
 * 
 * @author chenhaohao
 * 
 */
@Controller
public class IndexController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(IndexController.class);
	

	/** 管理员service */
	@Autowired
	private AdminService adminService;

	/** 角色service */
	@Autowired
	private RoleService roleService;
	/** 资源service */
	@Autowired
	private ResourceService resourceService;

	@ModelAttribute
	public void initData(HttpServletRequest request, Model model) {
		AdminDTO dto = SessionUtil.getLoginAdmin(request);
		AdminQO qo = new AdminQO();
		qo.setFetchRoles(true);
		if (null != dto) {
			qo.setId(dto.getId());
		}
		if (StringUtils.isNotBlank(qo.getId())) {

			Admin admin = adminService.queryUnique(qo);
			List<Role> listRole = null;
			List<Resource> listParent = new ArrayList<Resource>();
			List<Resource> listResource = new ArrayList<Resource>();
			for (Role role : admin.getRoles()) {
				RoleQO roleQO = new RoleQO();
				roleQO.setFetchResources(true);
				roleQO.setId(role.getId());
				listRole = roleService.queryList(roleQO);
				break;
			}

			for (Role role : listRole) {
				for (Resource resource : role.getResources()) {
					listResource.add(resource);
					if (resource.getId().equals(resource.getParent().getId())) {
						listParent.add(resource);
					}
				}
				break;
			}
			/**
			 * 资源父级list排序
			 */
			Collections.sort(listParent, new Comparator<Resource>() {
				public int compare(Resource resource, Resource resource1) {
					return resource.getSort().compareTo(resource1.getSort());
				}
			});

			/**
			 * 资源父级子级list排序
			 */
			Collections.sort(listResource, new Comparator<Resource>() {
				public int compare(Resource resource, Resource resource1) {
					return resource.getSort().compareTo(resource1.getSort());
				}
			});
			model.addAttribute("listResource", listResource);
			model.addAttribute("listParent", listParent);
		}
	}

	/**
	 * 入口页面
	 * 
	 * @return 页面路径
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {

		return "/index.html";
	}

	/**
	 * 主页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {

		return "/main.html";
	}

	@RequestMapping("/sms/test")
	public void smsTest() {
		// SmsClient.sendLoginCode("15935150361", "hehe", 60);
	}

	/**
	 * <p>
	 * ueditor后台请求入口
	 * </p>
	 * <ol>
	 * <li>GET请求代表加载ueditor配置信息</li>
	 * <li>POST请求（上传图片、视频、文件等）</li>
	 * </ol>
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/ueditor_config")
	public void ueditorConfig(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html, charset=utf-8");
		try {
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String configJSON = new ActionEnter(request, rootPath).exec();
			response.getWriter().write(configJSON);
		} catch (IOException e) {
			logger.error(
					"【UEditor配置加载异常】IndexController.ueditorConfig(request, response)"
							+ e.getMessage(), e);
		}
	}
	
	@RequestMapping(value = "/yy")
	public String yy(HttpServletRequest request,
			HttpServletResponse response) {
				return "/yy.html";
		
	}


	/**
	 * 单、多图片上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload_image", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			return FileUploader.imageUpload(request);
		} catch (Exception e) {
			logger.error(
					"【图片上传异常】IndexController.uploadFile(request, response)"
							+ e.getMessage(), e);
		}
		return "";
	}
	
	/**
	 * 地图坐标抓取
	 * 
	 * @return
	 */
	@RequestMapping("/query/map")
	public String queryMap(HttpServletRequest request) {
		return "/public/maps.html";
	}
}
