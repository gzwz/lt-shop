package lt.admin.web.contorller.product;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.product.command.CreateProductParameterValueCommand;
import lt.product.command.ModifyProductParameterValueCommand;
import lt.product.dto.ProductParameterValueDTO;
import lt.product.entity.ProductParameterValue;
import lt.product.qo.ProductParameterValueQO;
import lt.product.service.ProductParameterValueService;

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
@RequestMapping(value = "/productParameterValue")
public class ProductParameterValueController extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(ProductParameterController.class);

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

		return "/product/productParameterValue/view.html";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, ProductParameterValueQO qo) {
      
		qo.setFetchProductParameter(true);
		qo.setBatchResult(true);
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = productParameterValueService.queryPagination(pagination);
			pagination.setList(ProductParameterValueDTO.domainToDTO((List<ProductParameterValue>) pagination.getList()));
		} catch (Exception e) {
			logger.error("【查询异常】ProductParameterController.query(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateProductParameterValueCommand command) {

		try {
			productParameterValueService.createProductParameterValue(command);
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
	@ResponseBody
	public String edit(HttpServletRequest request, ProductParameterValueQO qo) {
		ProductParameterValue pc = null;
		try {
			qo.setFetchProductParameter(true);
			pc = productParameterValueService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【编辑查询异常】ProductParameterController.edit(request, qo)"
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
			ModifyProductParameterValueCommand command) {

		try {
			productParameterValueService.modfiyProductParameterValue(command);
		} catch (Exception e) {
			logger.error(
					"【修改异常】ProductParameterController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

	/*@RequestMapping("/getProductParameterValue")
	@ResponseBody
	public String getProductParameterValue(HttpServletRequest request,
			ProductParameterValueQO qo) {

		List<ProductParameterValue> productParameterValue = null;
		try {
			qo.setProductParameterQO(new ProductParameterQO());
			if(StringUtils.isNotBlank(qo.getProductParameterId())){
				qo.getProductParameterQO().setId(qo.getProductParameterId());
			}
			productParameterValue=productParameterValueService.queryList(qo);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(productParameterValue);
	}*/
}
