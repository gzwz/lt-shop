package lt.admin.web.contorller.product;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.product.command.CreateProductParameterCommand;
import lt.product.command.ModifyProductParameterCommand;
import lt.product.dto.ProductParameterDTO;
import lt.product.dto.ProductParameterValueDTO;
import lt.product.entity.ProductParameter;
import lt.product.entity.ProductParameterValue;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ProductParameterQO;
import lt.product.qo.ProductParameterValueQO;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductParameterService;
import lt.product.service.ProductParameterValueService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/productParameter")
public class ProductParameterController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(ProductParameterController.class);

	/** service */
	@Autowired
	private ProductParameterService productParameterService;
	
	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/** service */
	@Autowired
	private ProductParameterValueService productParameterValueService;


	/**
	 * 跳转资源类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model) {

		return "/product/productParameter/view.html";
	}
	
	
	
	
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/product/productParameter/add.html";
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, ProductParameterQO qo) {

		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = productParameterService.queryPagination(pagination);
			pagination.setList(ProductParameterDTO.domainToDTO((List<ProductParameter>) pagination.getList()));
		} catch (Exception e) {
			logger.error("【查询异常】ProductParameterController.query(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateProductParameterCommand command) {
		
		List<String> list=new ArrayList<String>();
		for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
			list.add(command.getProductCategoryIds().split(",")[i]);
		}
		if(list.size()>0){
			command.setProductCatyIds(list);
		}
		try {
			productParameterService.createProductParameter(command);
		} catch (Exception e) {
			logger.error(
					"【新增异常】ProductParameterController.save(request, command)"
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
	public String edit(HttpServletRequest request, ProductParameterQO qo,Model model) {
		ProductParameter pc = null;
		try {
			qo.setFetchProductCategory(true);
			qo.setBatchResult(true);
			pc = productParameterService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【编辑查询异常】ProductParameterController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		model.addAttribute("pc", pc);
		return "/product/productParameter/edit.html";
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
			ModifyProductParameterCommand command) {

		List<String> list=new ArrayList<String>();
		for(int i=0;i<command.getProductCategoryIds().split(",").length;i++){
			list.add(command.getProductCategoryIds().split(",")[i]);
		}
		if(list.size()>0){
			command.setProductCatyIds(list);
		}
		try {
			productParameterService.modify(command);
		} catch (Exception e) {
			logger.error(
					"【修改异常】ProductParameterController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

	@RequestMapping("/getProductParameter")
	@ResponseBody
	public String getProductParameter(HttpServletRequest request,
			ProductParameterQO qo) {
		List<ProductParameterDTO> productParameterDTO = new ArrayList<ProductParameterDTO>();
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
			List<ProductParameter> productParameterList=productParameterService.queryList(qo);
				psiqList(productParameterList);
				for(ProductParameter pt:productParameterList){
					ProductParameterDTO dto = new ProductParameterDTO();
					dto.setId(pt.getId());
					dto.setName(pt.getName());
					List<ProductParameterValueDTO> productParameterValueDTO = new ArrayList<ProductParameterValueDTO>();
					
					ProductParameterValueQO productParameterValueQO=new ProductParameterValueQO();
					productParameterValueQO.setFetchProductParameter(true);
					productParameterValueQO.setOrderBy(1);
					productParameterValueQO.setProductParameterQO(new ProductParameterQO());
					productParameterValueQO.getProductParameterQO().setId(pt.getId());
					List<ProductParameterValue> productParameterValueList=productParameterValueService.queryList(productParameterValueQO);
					psiqList(productParameterValueList);
					for(ProductParameterValue pv:productParameterValueList){
						ProductParameterValueDTO pvdto=new ProductParameterValueDTO();
						pvdto.setId(pv.getId());
						pvdto.setName(pv.getValue());
						if(StringUtils.isNotBlank(pv.getParameter().getId())){
							pvdto.setParamterId(pv.getParameter().getId());
						}
						productParameterValueDTO.add(pvdto);
					}
					
					dto.setProductParameterValueDTO(productParameterValueDTO);
					productParameterDTO.add(dto);
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(productParameterDTO);
	}
    
	
	
	@RequestMapping("/getPdt")
	@ResponseBody
	public String getPdt(HttpServletRequest request,
			ProductParameterQO qo) {
		List<ProductParameter> productParameter =null;
		try {
			productParameter = productParameterService.queryList(qo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(productParameter);
	}
	
}
