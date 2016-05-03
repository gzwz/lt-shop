package lt.admin.web.contorller.oneBuy;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.oneBuy.command.CreateCloudBrandCommand;
import lt.oneBuy.command.ModifyCloudBrandCommand;
import lt.oneBuy.dto.CloudBrandDTO;
import lt.oneBuy.entity.CloudBrand;
import lt.oneBuy.qo.CloudBrandQO;
import lt.oneBuy.qo.CloudCategoryQO;
import lt.oneBuy.service.CloudBrandService;
import lt.oneBuy.service.CloudCategoryService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/cloudBrand")
public class CloudBrandController extends BaseController {
	

	/** service */
	@Autowired
	private CloudBrandService cloudBrandService;
	
	
	/** service */
	@Autowired
	private CloudCategoryService cloudCategoryService;
	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model,CloudBrandQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("productCategoryList", cloudBrandService.queryList(qo));
		return "/oneBuy/cloudBrand/view.html";
	}
	
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/oneBuy/cloudBrand/add.html";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateCloudBrandCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getCloudCategoryId().split(",").length;i++){
				list.add(command.getCloudCategoryId().split(",")[i]);
			}
			if(CollectionUtils.isNotEmpty(list)){
				command.setCloudCategoryIds(list);
			}
			cloudBrandService.createCloudBrand(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request){
		
		CloudBrandQO qo =new CloudBrandQO();
		qo.setFetchCloudBrand(true);
		List<CloudBrand> productBrandList=cloudBrandService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (CloudBrand authPerm : productBrandList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getBrandName());
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
	public String edit(HttpServletRequest request, CloudBrandQO qo,Model model) {
		CloudBrand pc = null;
		try {
			qo.setFetchCloudBrand(true);
			pc = cloudBrandService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pc", pc);
		return "/oneBuy/cloudBrand/edit.html";
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
			ModifyCloudBrandCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getCloudCategoryId().split(",").length;i++){
				list.add(command.getCloudCategoryId().split(",")[i]);
			}
			if(CollectionUtils.isNotEmpty(list)){
				command.setCloudCategoryIds(list);
			}
			cloudBrandService.modifyCloudBrand(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

	
	@RequestMapping("/getCloudBrand")
	@ResponseBody
	public String getCloudBrand(HttpServletRequest request,CloudBrandQO qo){
		
		List<CloudBrand>  productBrand=null;
		try {
			qo.setOrderBy(1);
			qo.setFetchCloudCategory(true);
			qo.setCloudCategoryQO(new CloudCategoryQO());
			List<String> ids=new ArrayList<String>();
			if(StringUtils.isNotBlank(qo.getCloudCategoryIds())){
				for(int i=0;i<qo.getCloudCategoryIds().split(",").length;i++){
					ids.add(qo.getCloudCategoryIds().split(",")[i]);
					qo.getCloudCategoryQO().setIds(ids);
				}
			}
			productBrand=cloudBrandService.queryList(qo);
			psiqList(productBrand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(CloudBrandDTO.domainToDTO(productBrand));
	}

}
