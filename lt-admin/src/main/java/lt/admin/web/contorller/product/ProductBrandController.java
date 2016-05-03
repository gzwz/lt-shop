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
import lt.product.command.CreateProductBrandCommand;
import lt.product.command.ModifyProductBrandCommand;
import lt.product.dto.ProductBrandDTO;
import lt.product.entity.ProductBrand;
import lt.product.qo.ProductBrandQO;
import lt.product.qo.ProductCategoryQO;
import lt.product.service.ProductBrandService;
import lt.product.service.ProductCategoryService;

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
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/productBrand")
public class ProductBrandController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(ProductBrandController.class);

	/** service */
	@Autowired
	private ProductBrandService productBrandService;
	
	
	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;
	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request,Model model,ProductBrandQO qo) {
		qo.setOrderBy(1);
		model.addAttribute("productCategoryList", productBrandService.queryList(qo));
		return "/product/productBrand/view.html";
	}
	
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/product/productBrand/add.html";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateProductBrandCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
				list.add(command.getProductCategoryIds().split(",")[i]);
			}
			if(list.size()>0){
				command.setProductCatyIds(list);
			}
			productBrandService.createProductBrand(command);
		} catch (Exception e) {
			logger.error(
					"【新增异常】ProductBrandController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "新增成功");
	}
	
	
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String getPermData(HttpServletRequest request, Model model,String id){
		
		ProductBrandQO qo =new ProductBrandQO();
		qo.setFetchProductBrand(true);
		List<ProductBrand> productBrandList=productBrandService.queryList(qo);
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		for (ProductBrand authPerm : productBrandList) {
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
	public String edit(HttpServletRequest request, ProductBrandQO qo,Model model) {
		ProductBrand pc = null;
		try {
			qo.setFetchProductBrand(true);
			pc = productBrandService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【编辑查询异常】ProductBrandController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		model.addAttribute("pc", pc);
		return "/product/productBrand/edit.html";
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
			ModifyProductBrandCommand command) {

		try {
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
				list.add(command.getProductCategoryIds().split(",")[i]);
			}
			if(list.size()>0){
				command.setProductCatyIds(list);
			}
			productBrandService.modifyProductBrand(command);
		} catch (Exception e) {
			logger.error(
					"【修改异常】ProductBrandController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

	@RequestMapping("/getProductBrand")
	@ResponseBody
	public String getProductBrand(HttpServletRequest request,ProductBrandQO qo){
		
		List<ProductBrand>  productBrand=null;
		try {
			qo.setOrderBy(1);
			qo.setFetchProductCategory(true);
			qo.setProductCategoryQO(new ProductCategoryQO());
			List<String> ids=new ArrayList<String>();
			if(StringUtils.isNotBlank(qo.getProductCategoryIds())){
				for(int i=0;i<qo.getProductCategoryIds().split(",").length;i++){
					ids.add(qo.getProductCategoryIds().split(",")[i]);
					qo.getProductCategoryQO().setIds(ids);
				}
			}
			productBrand=productBrandService.queryList(qo);
			psiqList(productBrand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(ProductBrandDTO.domainToDTO(productBrand));
	}
	
	
	
}
