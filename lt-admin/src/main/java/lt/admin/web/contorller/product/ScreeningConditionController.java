package lt.admin.web.contorller.product;

import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.product.command.CreateScreeningConditionCommand;
import lt.product.command.ModifyScreeningConditionCommand;
import lt.product.entity.ScreeningCondition;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ScreeningConditionQO;
import lt.product.service.ScreeningConditionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/screeningCondition")
public class ScreeningConditionController extends BaseController{

	/** service */
	@Autowired
	private ScreeningConditionService screeningConditionService;
	
	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model,ScreeningConditionQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("productCategoryList", screeningConditionService.queryList(qo));
		return "/product/screening/view.html";
	}
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request,Model model) {
		return "/product/screening/add.html";
	}
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request, Model model,String id,String productCategoryIds){
		
		ScreeningConditionQO qo =new ScreeningConditionQO();
		qo.setOrderBy(1);
		
		qo.setFetchScreeningCondition(true);
		
		if(StringUtils.isNotBlank(productCategoryIds)){
			qo.setProductCategoryQO(new ProductCategoryQO());
			qo.getProductCategoryQO().setId(productCategoryIds);
		}
		List<ScreeningCondition> screeningConditionList=screeningConditionService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ScreeningCondition authPerm : screeningConditionList) {
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
	
	
	@RequestMapping(value="/getScreeningData")
	@ResponseBody
	public String getScreeningData(HttpServletRequest request, Model model,String id,String productCategoryIds){
		
		ScreeningConditionQO qo =new ScreeningConditionQO();
		qo.setOrderBy(1);
		
		qo.setFetchScreeningCondition(true);
		
		if(StringUtils.isNotBlank(productCategoryIds)){
			qo.setProductCategoryQO(new ProductCategoryQO());
			qo.getProductCategoryQO().setId(productCategoryIds);
		}
		List<ScreeningCondition> screeningConditionList=screeningConditionService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ScreeningCondition authPerm : screeningConditionList) {
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
				for (ScreeningCondition ap : screeningConditionList) {
					if (authPerm.getId().equals(ap.getId())) {
						map.put("checked", true);
						break;
					}
				}
			}
			Random random = new Random();
			int num = random.nextInt(5);
			if (num % 5 == 0) {
				map.put("icon", "../resources/ztree/css/zTreeStyle/img/diy/2.png");
			} else if (num % 5 == 1) {
				map.put("icon", "../resources/ztree/css/zTreeStyle/img/diy/3.png");
			} else if (num % 5 == 2) {
				map.put("icon", "../resources/ztree/css/zTreeStyle/img/diy/5.png");
			} else if (num % 5 == 3) {
				map.put("icon", "../resources/ztree/css/zTreeStyle/img/diy/7.png");
			} else if (num % 5 == 4) {
				map.put("icon", "../resources/ztree/css/zTreeStyle/img/diy/8.png");
			}
			strList.add(map);
		}

		return JSON.toJSONString(strList);
	}
	
	
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateScreeningConditionCommand command) {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
			list.add(command.getProductCategoryIds().split(",")[i]);
		}
		if(list.size()>0){
			command.setProductCatyIds(list);
		}
		try {
			screeningConditionService.createScreeningCondition(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	
	
	/**
	 * 跳转类型编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request,ScreeningConditionQO qo,Model model) {
		ScreeningCondition pc = null;
		try {
			qo.setFetchScreeningCondition(true);
			pc = screeningConditionService.queryUnique(qo);
		} catch (Exception e) {
				e.printStackTrace();
		}
		model.addAttribute("pc", pc);
		return "/product/screening/edit.html";
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
			ModifyScreeningConditionCommand command) {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
			list.add(command.getProductCategoryIds().split(",")[i]);
		}
		if(list.size()>0){
			command.setProductCatyIds(list);
		}
		try {
			screeningConditionService.modifyScreeningCondition(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	
}
