package lt.admin.web.contorller.product;

import java.util.ArrayList;
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
import lt.merchant.entity.Merchant;
import lt.merchant.qo.MerchantQO;
import lt.merchant.service.MerchantService;
import lt.product.command.CreateProductCommand;
import lt.product.command.DisableProductCommand;
import lt.product.command.EnableProductCommand;
import lt.product.command.ModifyProductCommand;
import lt.product.command.ModifySKUProductCommand;
import lt.product.dto.ProductDTO;
import lt.product.entity.Product;
import lt.product.entity.ProductStatistics;
import lt.product.entity.SKUProduct;
import lt.product.qo.ProductQO;
import lt.product.qo.ProductStatisticsQO;
import lt.product.qo.SKUProductQO;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductService;
import lt.product.service.ProductStatisticsService;
import lt.product.service.SKUProductService;
import lt.product.service.SkuItemsService;

import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping(value = "/product")
public class ProductController extends BaseController{
	/**
	 * 日子对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ProductController.class);
	/**
	 * service注解
	 */
	@Autowired
	private ProductService productService;
	
	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private SkuItemsService skuItemsService;
	
	@Autowired
	private ImageService imageService;
	
	/** 店铺service */
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private SKUProductService sKUProductService;
	
	@Autowired
	private ProductStatisticsService productStatisticsService;
    
	
	/**
	 * @param request
	 * @param qo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, ProductQO qo) {
		Pagination pagination = new Pagination();
		qo.setNameLike(true);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = productService.queryPagination(pagination);
			pagination.setList(ProductDTO.domainToDTO((List<Product>) pagination.getList()));
		} catch (Exception e) {
			logger.error("【查询异常】ProductController.query(request, qo)" + e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}
	
	
	@RequestMapping("/stock")
	@ResponseBody
	public String stock(HttpServletRequest request, SKUProductQO qo) {
		Pagination pagination = new Pagination();
		qo.setSkuSpecInfoLike(true);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = sKUProductService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【查询异常】ProductController.stock(request, qo)" + e.getMessage(), e);
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
	public String create(HttpServletRequest request,CreateProductCommand command) {
		try {
			
			String[] prices = request.getParameterValues("prices");
			String[] numbers = request.getParameterValues("numbers");
			String[] skuSpecInfos = request.getParameterValues("skuSpecInfos");
			String[] merchantIds = request.getParameterValues("merchantIds");
			
			List<String> list=new ArrayList<String>();
			for(int i=0;i<command.getScreeningId().split(",").length;i++){
				list.add(command.getScreeningId().split(",")[i]);
			}
			if(list.size()>0){
				command.setScreeningIds(list);
			}
			
			productService.createProduct(command, prices, numbers, skuSpecInfos,merchantIds);
		} catch (Exception e) {
			logger.error("【新增异常】ProductController.create(request, command)" + e.getMessage(), e);
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
	public String modify(ModifyProductCommand command) {
		try {
			productService.modifyProduct(command);
		} catch (Exception e) {
			logger.error("【编辑异常】ProductController.modify(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
	}


	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		return "/product/view.html";
	}
	
	@RequestMapping("/to-stock")
	public String toStock(HttpServletRequest request,String productId,Model model) {
		model.addAttribute("productId", productId);
		return "/product/stock.html";
	}

	
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Model model) {
		MerchantQO merchantQO=new MerchantQO();
		List<Merchant> merchantList= merchantService.queryList(merchantQO);
		model.addAttribute("merchantList", merchantList);
		return "/product/add.html";
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
	public String edit(HttpServletRequest request, Model model,ProductQO qo) {
	    qo.setFetchMerchant(true);
	    qo.setFetchProductBrand(true);
	    qo.setFetchProductCategory(true);
		Product product = productService.queryUnique(qo);
		model.addAttribute("product", product);
		/*
		SkuItemsQO skuItemsQO=new SkuItemsQO();
		skuItemsQO.setProductQO(new ProductQO());
		skuItemsQO.getProductQO().setId(qo.getId());
		List<SkuItems> skuItemsList=skuItemsService.queryList(skuItemsQO);
		List<String> valuePoperty=new ArrayList<String>();
		for(SkuItems skuItems:skuItemsList){
			for(int i=0;i<skuItems.getProportyValueItem().split(",").length;i++){
				valuePoperty.add(skuItems.getProportyValueItem().split(",")[i]);
			}
		}
		model.addAttribute("skuValueList", valuePoperty);*/
		
		
		ImageQO iqo = new ImageQO();
		iqo.setDomainId(product.getId());
		iqo.setDomainType("product_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String,String> mp=null;
		if(CollectionUtils.isNotEmpty(image)){
			 mp=(Map<String, String>) JSON.parse(image.get(0).getSpecImageMapJSON());
		}
		model.addAttribute("imageList", mp);
		
		MerchantQO merchantQO=new MerchantQO();
		List<Merchant> merchantList= merchantService.queryList(merchantQO);
		model.addAttribute("merchantList", merchantList);
		
		ProductStatisticsQO pstQO=new ProductStatisticsQO();
		pstQO.setProductQO(new ProductQO());
		pstQO.getProductQO().setId(qo.getId());
		ProductStatistics productStatistics=productStatisticsService.queryUnique(pstQO);
		model.addAttribute("pds", productStatistics);
		
		return "/product/edit.html";
	}
	
	
	
	
	/**
	 * 下架
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/disable")
	@ResponseBody
	public String disable(HttpServletRequest request, DisableProductCommand command) {

		try {
			productService.disable(command);
		} catch (Exception e) {
			logger.error(
					"【 下架异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, " 下架失败");
		}

		return ResultJSON.resultToJSONStr(true, " 下架成功");
	}

	/**
	 * 上架
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/enable")
	@ResponseBody
	public String enable(HttpServletRequest request,
			EnableProductCommand command) {

		try {
			productService.enable(command);
		} catch (Exception e) {
			logger.error(
					"【上架异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "上架失败");
		}

		return ResultJSON.resultToJSONStr(true, "上架成功");
	}
	
	
	
	/**
	 * 设置热卖
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/setting-hot")
	@ResponseBody
	public String settingHot(HttpServletRequest request,
			EnableProductCommand command) {

		try {
			productService.hotT(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "设置失败");
		}

		return ResultJSON.resultToJSONStr(true, "设置成功");
	}
	
	/**
	 * 设置非热卖
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/setting-no")
	@ResponseBody
	public String settingN(HttpServletRequest request,
			DisableProductCommand command) {

		try {
			productService.hotF(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "设置失败");
		}

		return ResultJSON.resultToJSONStr(true, "设置成功");
	}
	
	
	
	@RequestMapping(value = "/edit-stock")
	@ResponseBody
	public String editStock(HttpServletRequest request, SKUProductQO qo) {
		SKUProduct pc = null;
		try {
			pc = sKUProductService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【编辑查询异常】ProductCategoryController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(pc);
	}
	
	
	@RequestMapping(value = "/modif-stock")
	@ResponseBody
	public String modifStock(HttpServletRequest request, ModifySKUProductCommand command) {
		try {
			 sKUProductService.modifySKUProduct(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}

}
