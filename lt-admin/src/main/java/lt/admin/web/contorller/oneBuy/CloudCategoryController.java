package lt.admin.web.contorller.oneBuy;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.admin.web.contorller.BaseController;
import lt.oneBuy.command.CreateCloudCategoryCommand;
import lt.oneBuy.command.ModifyCloudCategoryCommand;
import lt.oneBuy.entity.CloudBrand;
import lt.oneBuy.entity.CloudCategory;
import lt.oneBuy.qo.CloudBrandQO;
import lt.oneBuy.qo.CloudCategoryQO;
import lt.oneBuy.service.CloudBrandService;
import lt.oneBuy.service.CloudCategoryService;

@Controller
@RequestMapping(value = "/cloudCategory")
public class CloudCategoryController extends BaseController {

	/** service */
	@Autowired
	private CloudCategoryService cloudCategoryService;

	/** service */
	@Autowired
	private CloudBrandService cloudBrandService;

	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model,
			CloudCategoryQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("productCategoryList",
				cloudCategoryService.queryList(qo));
		return "/oneBuy/cloudCategory/view.html";
	}

	@RequestMapping(value = "/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request, Model model, String id) {

		CloudCategoryQO qo = new CloudCategoryQO();
		qo.setFetchCloudCategory(true);
		List<CloudCategory> cloudCategoryList = cloudCategoryService
				.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (CloudCategory authPerm : cloudCategoryList) {
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

	@RequestMapping(value = "/getCloudBrandZtreeData")
	@ResponseBody
	public String getCloudBrandZtreeData(HttpServletRequest request,
			Model model, String id) {

		CloudCategoryQO qo = new CloudCategoryQO();
		qo.setFetchCloudCategory(true);
		List<CloudCategory> cloudCategoryList = cloudCategoryService
				.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (CloudCategory authPerm : cloudCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "0";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}

			List<CloudCategory> hasPermList = new ArrayList<CloudCategory>();
			List<CloudBrand> productBrandList = null;
			if (StringUtils.isNotBlank(id)) {

				CloudBrandQO productBrandQO = new CloudBrandQO();
				productBrandQO.setFetchCloudCategory(true);
				productBrandQO.setId(id);

				productBrandList = cloudBrandService.queryList(productBrandQO);

				for (CloudBrand productBrand : productBrandList) {
					hasPermList.addAll(productBrand.getCloudCategorys());
				}

			}

			map.put("id", authPerm.getId());
			map.put("name", authPerm.getName());
			map.put("pId", parentId);
			map.put("open", true);

			if (StringUtils.isNotBlank(id)) {
				for (CloudCategory ap : hasPermList) {
					if (authPerm.getId().equals(ap.getId())) {
						map.put("checked", true);
						break;
					}
				}
			}

			Random random = new Random();
			int num = random.nextInt(5);
			if (num % 5 == 0) {
				map.put("icon",
						"../resources/ztree/css/zTreeStyle/img/diy/2.png");
			} else if (num % 5 == 1) {
				map.put("icon",
						"../resources/ztree/css/zTreeStyle/img/diy/3.png");
			} else if (num % 5 == 2) {
				map.put("icon",
						"../resources/ztree/css/zTreeStyle/img/diy/5.png");
			} else if (num % 5 == 3) {
				map.put("icon",
						"../resources/ztree/css/zTreeStyle/img/diy/7.png");
			} else if (num % 5 == 4) {
				map.put("icon",
						"../resources/ztree/css/zTreeStyle/img/diy/8.png");
			}
			strList.add(map);
		}

		return JSON.toJSONString(strList);
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateCloudCategoryCommand command) {

		try {
			cloudCategoryService.createCloudCategory(command);
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
	@ResponseBody
	public String edit(HttpServletRequest request, CloudCategoryQO qo) {
		CloudCategory pc = null;
		try {
			qo.setFetchCloudCategory(true);
			pc = cloudCategoryService.queryUnique(qo);
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
			ModifyCloudCategoryCommand command) {
		try {
			cloudCategoryService.modify(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

}
