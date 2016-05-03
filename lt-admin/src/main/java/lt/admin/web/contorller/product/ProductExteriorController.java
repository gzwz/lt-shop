package lt.admin.web.contorller.product;

import java.util.List;
import java.util.Map;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.base.entity.Image;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.product.command.CreateProductExteriorCommand;
import lt.product.command.ModifyProductExteriorCommand;
import lt.product.entity.ProductExterior;
import lt.product.qo.ProductExteriorQO;
import lt.product.qo.ProductQO;
import lt.product.service.ProductExteriorService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/productExterior")
public class ProductExteriorController extends BaseController{

	@Autowired
	private ProductExteriorService productExteriorService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request,String productId,Model model) {
		
		model.addAttribute("productId", productId);
		return "/product/productExterior/view.html";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, ProductExteriorQO qo) {
		if(StringUtils.isNotBlank(qo.getProductId())){
			qo.setProductQO(new ProductQO());
			qo.getProductQO().setId(qo.getProductId());
		}
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = productExteriorService.queryPagination(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pagination);
	}
	
	
 
	/**
	 * 新增数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(CreateProductExteriorCommand command) {
		try {
			productExteriorService.createProductExterior(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "新增成功");
	}

	/**
	 * 修改数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(ModifyProductExteriorCommand command) {
		try {
			productExteriorService.modifyProductExterior(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,String productId,Model model) {
		model.addAttribute("productId", productId);
		return "/product/productExterior/add.html";
	}

	/**
	 * 根据id获得数据
	 * @param request
	 * @param model
	 * @param navigationId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model, ProductExteriorQO qo) {
		qo.setFetchProduct(true);
		ProductExterior productExterior = productExteriorService.queryUnique(qo);
		model.addAttribute("productExterior", productExterior);
		
		ImageQO iqo = new ImageQO();
		iqo.setDomainId(productExterior.getId());
		iqo.setDomainType("product_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String,String> mp=(Map<String, String>) JSON.parse(image.get(0).getSpecImageMapJSON());
		model.addAttribute("imageList", mp);
		
		return "/product/productExterior/edit.html";
	}
	
	/**
	 * 根据id删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			for(int i=0;i<id.split(",").length;i++){
				productExteriorService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "删除成功");
	}
	

}
