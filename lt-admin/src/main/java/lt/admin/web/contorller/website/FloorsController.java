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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.admin.web.contorller.BaseController;
import lt.sitepc.command.CreateFloorsCommand;
import lt.sitepc.command.ModifyFloorsCommand;
import lt.sitepc.entity.Floors;
import lt.sitepc.qo.FloorsQO;
import lt.sitepc.service.FloorsService;
/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/floors")
public class FloorsController extends BaseController{
	
	@Autowired
	private FloorsService floorsService;

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model,FloorsQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("floorsList", floorsService.queryList(qo));
		return "/webSite/floors/view.html";
	}
	
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/webSite/floors/add.html";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateFloorsCommand command) {

		try {
			floorsService.createFloors(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request, Model model,String id){
		
		FloorsQO qo =new FloorsQO();
		qo.setFetchFloors(true);
		List<Floors> productBrandList=floorsService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (Floors authPerm : productBrandList) {
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
	
	
	/**
	 * 跳转类型编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request,FloorsQO qo,Model model) {
		Floors pc = null;
		try {
			qo.setFetchFloors(true);
			pc = floorsService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pc", pc);
		return "/webSite/floors/edit.html";
	}
	
	/**
	 * 
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			ModifyFloorsCommand command) {

		try {
			floorsService.modifyFloors(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

}
