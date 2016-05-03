package lt.admin.web.contorller.sys;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.content.command.CreateNavigationItemCommand;
import lt.content.command.ModifyNavigationItemCommand;
import lt.content.entity.NavigationItem;
import lt.content.qo.NavigationItemQO;
import lt.content.service.NavigationItemService;

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
 * 导航菜单
 * @author wxp
 *
 */
@Controller
@RequestMapping("/system/navigation")
public class NavigationItemController extends BaseController{
	/**
	 * 日子对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(NavigationItemController.class);
	/**
	 * service注解
	 */
	@Autowired
	private NavigationItemService navigationItemService;
    
	/**
	 * 导航查询
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, NavigationItemQO qo) {
		Pagination pagination = new Pagination();
		qo.setOrderBy(1);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = navigationItemService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【导航查询异常】NavigationItemController.query(request, qo)" + e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}
	
	
 
	/**
	 * 新增数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(CreateNavigationItemCommand command) {
		try {
			navigationItemService.createNavigationItem(command);
		} catch (Exception e) {
			logger.error("【导航新增异常】NavigationItemController.create(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "导航新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "导航新增成功");
	}

	/**
	 * 修改数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(ModifyNavigationItemCommand command) {
		try {
			navigationItemService.modifyNavigationItem(command);
		} catch (Exception e) {
			logger.error("【导航编辑异常】NavigationItemController.modify(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "导航编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
	}


	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		return "/admin/naviga/view.html";
	}

	
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		return "/admin/naviga/add.html";
	}

	/**
	 * 根据id获得数据
	 * @param request
	 * @param model
	 * @param navigationId
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model, String navigationId) {
		NavigationItemQO qo = new NavigationItemQO();
		qo.setId(navigationId);
		NavigationItem navigationItem = navigationItemService.queryUnique(qo);
		model.addAttribute("navigationItem", navigationItem);
		return "/admin/naviga/edit.html";
	}
	
	/**
	 * 根据id删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			for(int i=0;i<id.split(",").length;i++){
				navigationItemService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error("【导航删除异常】NavigationItemController.del(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "导航删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "导航删除成功");
	}

}
