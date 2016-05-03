package lt.admin.web.contorller.website;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.admin.web.contorller.BaseController;
import lt.sitepc.command.CreateMenuContentsCommand;
import lt.sitepc.command.ModifyMenuContentsCommand;
import lt.sitepc.entity.MenuContents;
import lt.sitepc.qo.MenuContentsQO;
import lt.sitepc.service.MenuContentsService;
/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/menuContents")
public class MenuContentsController extends BaseController{

	
	@Autowired
	private MenuContentsService menuContentsService;
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(HttpServletRequest request,CreateMenuContentsCommand command) {
		try {
			menuContentsService.createMenuContents(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	

	@RequestMapping(value = "/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,ModifyMenuContentsCommand command) {

		try {
			menuContentsService.modifyMenuContents(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			menuContentsService.deleteById(id);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}
		return ResultJSON.resultToJSONStr(true, "删除成功");
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public String query(HttpServletRequest request, MenuContentsQO qo) {
		Pagination pagination = new Pagination();
		try {
			qo.setFetchBottomMenu(true);
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = menuContentsService.queryPagination(pagination);
		} catch (Exception e) {
		}

		return JSONUtils.c(pagination);
	}
	

	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		
		return "/webSite/menuContents/view.html";
	}
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/webSite/menuContents/add.html";
	}
	
	

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, MenuContentsQO qo,Model model) {
		MenuContents pc = null;
		try {
			qo.setFetchBottomMenu(true);
			pc = menuContentsService.queryUnique(qo);
		} catch (Exception e) {
		}
		model.addAttribute("pc", pc);
		return "/webSite/menuContents/edite.html";
	}
}
