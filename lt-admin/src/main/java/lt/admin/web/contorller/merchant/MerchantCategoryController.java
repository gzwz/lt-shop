package lt.admin.web.contorller.merchant;

import gzlazypack.common.util.JSONUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.merchant.command.CreateMerchantCategoryCommand;
import lt.merchant.command.ModifyMerchantCategoryCommand;
import lt.merchant.entity.MerchantCategory;
import lt.merchant.qo.MerchantCategoryQO;
import lt.merchant.service.MerchantCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
/**
 * 店铺类型
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/merchantCategory")
public class MerchantCategoryController extends BaseController{

	/** 店铺类型service */
	@Autowired
	private MerchantCategoryService merchantCategoryService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model,MerchantCategoryQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("merchantCategoryList", merchantCategoryService.queryList(qo));
		return "/merchant/merchantCategory/view.html";
	}
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String add(HttpServletRequest request, Model model){
		
		MerchantCategoryQO qo =new MerchantCategoryQO();
		qo.setFetchMerchantCategory(true);
		List<MerchantCategory> cateyoryList=merchantCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (MerchantCategory authPerm : cateyoryList) {
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

	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateMerchantCategoryCommand command) {
		return merchantCategoryService.createMerchantCategory(command);
	}
	
	
	/**
	 * 跳转类型编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request, MerchantCategoryQO qo) {
		MerchantCategory pc = null;
		try {
			qo.setFetchMerchantCategory(true);
			pc = merchantCategoryService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pc);
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
			ModifyMerchantCategoryCommand command) {
		return merchantCategoryService.modifyMerchantCategory(command);
	}
	
	
	@RequestMapping("/getMerchantCategory")
	@ResponseBody
	public String getMerchantCategory(HttpServletRequest request,MerchantCategoryQO qo){
		
		List<MerchantCategory>  merchantCategory=null;
		try {
			merchantCategory=merchantCategoryService.queryList(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(merchantCategory);
	}

}
