package lt.admin.web.contorller.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;


import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.product.command.CreateProductCategoryCommand;
import lt.product.command.ModifyProductCategoryCommand;
import lt.product.entity.ProductBrand;
import lt.product.entity.ProductCategory;
import lt.product.entity.ProductParameter;
import lt.product.entity.ScreeningCondition;
import lt.product.qo.ProductBrandQO;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ProductParameterQO;
import lt.product.qo.ScreeningConditionQO;
import lt.product.service.ProductBrandService;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductParameterService;
import lt.product.service.ScreeningConditionService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


/**
 * 类型
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/productCategory")
public class ProductCategoryController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(ProductCategoryController.class);

	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/** service */
	@Autowired
	private ProductParameterService productParameterService;
	
	/** service */
	@Autowired
	private ProductBrandService productBrandService;
	
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
	public String view(HttpServletRequest request,Model model,ProductCategoryQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("productCategoryList", productCategoryService.queryList(qo));
		return "/product/productCategory/view.html";
	}
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ProductParameter> productParameterList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ProductParameterQO productParameterQO = new ProductParameterQO();
				productParameterQO.setFetchProductCategory(true);
				productParameterQO.setId(id);
				
				productParameterList= productParameterService.queryList(productParameterQO);
				
				for(ProductParameter productParameter:productParameterList){
					hasPermList.addAll(productParameter.getProductCategorys());
				}
				
			}
			
			

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getName());
			map.put("parent", parentId);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("opened", true);


			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
	
	
	
	
	@RequestMapping(value="/getPaterZtreeData")
	@ResponseBody
	public String getPaterZtreeData(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "0";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ProductParameter> productParameterList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ProductParameterQO productParameterQO = new ProductParameterQO();
				productParameterQO.setFetchProductCategory(true);
				productParameterQO.setId(id);
				
				productParameterList= productParameterService.queryList(productParameterQO);
				
				for(ProductParameter productParameter:productParameterList){
					hasPermList.addAll(productParameter.getProductCategorys());
				}
				
			}
			
			

			map.put("id", authPerm.getId());
			map.put("name", authPerm.getName());
			map.put("pId", parentId);
			map.put("open", true);
            

			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
	
	
	
	@RequestMapping(value="/getProductBrandData")
	@ResponseBody
	public String add(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ProductBrand> productBrandList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ProductBrandQO productBrandQO = new ProductBrandQO();
				productBrandQO.setFetchProductCategory(true);
				productBrandQO.setId(id);
				
				productBrandList= productBrandService.queryList(productBrandQO);
				
				for(ProductBrand productBrand:productBrandList){
					hasPermList.addAll(productBrand.getProductCategorys());
				}
				
			}
			
			

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getName());
			map.put("parent", parentId);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("opened", true);


			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
	
	
	
	@RequestMapping(value="/getProductBrandZtreeData")
	@ResponseBody
	public String getProductBrandZtreeData(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "0";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ProductBrand> productBrandList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ProductBrandQO productBrandQO = new ProductBrandQO();
				productBrandQO.setFetchProductCategory(true);
				productBrandQO.setId(id);
				
				productBrandList= productBrandService.queryList(productBrandQO);
				
				for(ProductBrand productBrand:productBrandList){
					hasPermList.addAll(productBrand.getProductCategorys());
				}
				
			}
			
			

			map.put("id", authPerm.getId());
			map.put("name", authPerm.getName());
			map.put("pId", parentId);
			map.put("open", true);


			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
	
	
	
	@RequestMapping(value="/getScreeningConditionData")
	@ResponseBody
	public String addScreeningCondition(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ScreeningCondition> screeningConditionList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ScreeningConditionQO screeningConditionQO = new ScreeningConditionQO();
				screeningConditionQO.setFetchProductCategory(true);
				screeningConditionQO.setId(id);
				
				screeningConditionList= screeningConditionService.queryList(screeningConditionQO);
				
				for(ScreeningCondition screeningCondition:screeningConditionList){
					hasPermList.addAll(screeningCondition.getProductCategorys());
				}
				
			}
			
			

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getName());
			map.put("parent", parentId);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("opened", true);


			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
	
	
	
	@RequestMapping(value="/getScreeningConditionZtree")
	@ResponseBody
	public String getScreeningConditionZtree(HttpServletRequest request, Model model,String id){
		
		ProductCategoryQO qo =new ProductCategoryQO();
		qo.setFetchProductCategory(true);
		List<ProductCategory> productCategoryList=productCategoryService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductCategory authPerm : productCategoryList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "0";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}
			
			List<ProductCategory> hasPermList = new  ArrayList<ProductCategory>();
			List<ScreeningCondition> screeningConditionList = null;
			if (StringUtils.isNotBlank(id)) {
				
				ScreeningConditionQO screeningConditionQO = new ScreeningConditionQO();
				screeningConditionQO.setFetchProductCategory(true);
				screeningConditionQO.setId(id);
				
				screeningConditionList= screeningConditionService.queryList(screeningConditionQO);
				
				for(ScreeningCondition screeningCondition:screeningConditionList){
					hasPermList.addAll(screeningCondition.getProductCategorys());
				}
				
			}

			map.put("id", authPerm.getId());
			map.put("name", authPerm.getName());
			map.put("pId", parentId);
			map.put("open", true);


			if (StringUtils.isNotBlank(id)) {
				for (ProductCategory ap : hasPermList) {
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
			CreateProductCategoryCommand command) {

		try {
			productCategoryService.createProductCategory(command);
		} catch (Exception e) {
			logger.error(
					"【新增异常】ProductCategoryController.save(request, command)"
							+ e.getMessage(), e);
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
	public String edit(HttpServletRequest request, ProductCategoryQO qo) {
		ProductCategory pc = null;
		try {
			qo.setFetchProductCategory(true);
			pc = productCategoryService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【编辑查询异常】ProductCategoryController.edit(request, qo)"
					+ e.getMessage(), e);
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
			ModifyProductCategoryCommand command) {

		try {
			productCategoryService.modifyProductCategory(command);
		} catch (Exception e) {
			logger.error(
					"【修改异常】ProductCategoryController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	
	@RequestMapping("/getProductCategory")
	@ResponseBody
	public String getProductCategory(HttpServletRequest request,ProductCategoryQO qo){
		
		List<ProductCategory>  productCategory=null;
		try {
			if(StringUtils.isNoneBlank(qo.getProductCategoryId())){
			    qo.setFetchProductCategory(true);
				qo.setParendQO(new ProductCategoryQO());
				qo.getParendQO().setId(qo.getProductCategoryId());
			}
			productCategory=productCategoryService.queryList(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(productCategory);
	}

}
