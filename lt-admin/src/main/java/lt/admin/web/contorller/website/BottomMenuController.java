package lt.admin.web.contorller.website;

import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.admin.web.contorller.BaseController;
import lt.sitepc.command.CreateBottoMenuCommand;
import lt.sitepc.command.ModifyBottomMenuCommand;
import lt.sitepc.entity.BottomMenu;
import lt.sitepc.qo.BottomMenuQO;
import lt.sitepc.service.BottomMenuService;
/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/bottomMemu")
public class BottomMenuController extends BaseController{

	@Autowired
	private BottomMenuService bottomMenuService;
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/webSite/bottomMemu/add.html";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,CreateBottoMenuCommand command) {
		try {
			bottomMenuService.createBottomMenu(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request){
		
		BottomMenuQO qo =new BottomMenuQO();
		qo.setFetchBottomMenu(true);
		List<BottomMenu> bottomMenuList=bottomMenuService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();

		for (BottomMenu authPerm : bottomMenuList) {
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

	@RequestMapping(value ="/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,ModifyBottomMenuCommand command) {
		try {
			bottomMenuService.modifyBottomMenu(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			bottomMenuService.deleteById(id);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}
		return ResultJSON.resultToJSONStr(true, "删除成功");
	}


	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model, BottomMenuQO qo) {
		qo.setFetchBottomMenu(true);
		qo.setOrderBy(1);
		model.addAttribute("bottomMenuList", bottomMenuService.queryList(qo));
		return "/webSite/bottomMemu/view.html";
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request,Model model, BottomMenuQO qo) {
		BottomMenu pc = null;
		try {
			qo.setFetchBottomMenu(true);
			pc = bottomMenuService.queryUnique(qo);
		} catch (Exception e) {
			
		}
		model.addAttribute("pc", pc);
		return "/webSite/bottomMemu/edit.html";
	}
}
