package lt.pc.web.contorller.product;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.base.entity.Image;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.content.entity.NavigationItem;
import lt.content.qo.NavigationItemQO;
import lt.content.service.NavigationItemService;
import lt.merchant.entity.MerchantContactInfo;
import lt.merchant.qo.MerchantContactInfoQO;
import lt.merchant.qo.MerchantQO;
import lt.merchant.service.MerchantContactInfoService;
import lt.merchant.service.MerchantService;
import lt.pc.web.contorller.BaseController;
import lt.product.dto.ProductBrandDTO;
import lt.product.entity.Product;
import lt.product.entity.ProductBrand;
import lt.product.entity.ProductExterior;
import lt.product.entity.ProductStatistics;
import lt.product.entity.SKUProduct;
import lt.product.entity.SkuItems;
import lt.product.qo.ProductBrandQO;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ProductExteriorQO;
import lt.product.qo.ProductQO;
import lt.product.qo.ProductStatisticsQO;
import lt.product.qo.SKUProductQO;
import lt.product.qo.SkuItemsQO;
import lt.product.service.ProductBrandService;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductExteriorService;
import lt.product.service.ProductService;
import lt.product.service.ProductStatisticsService;
import lt.product.service.SKUProductService;
import lt.product.service.ScreeningConditionService;
import lt.product.service.SkuItemsService;
import lt.utils.PageUtils;

/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

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

	/** service */
	@Autowired
	private ScreeningConditionService screeningConditionService;

	/**
	 * service注解
	 */
	@Autowired
	private NavigationItemService navigationItemService;

	/** service */
	@Autowired
	private ProductBrandService productBrandService;

	@Autowired
	private ProductExteriorService productExteriorService;

	@Autowired
	private MerchantContactInfoService merchantContactInfoService;
	

	@RequestMapping(value = "/car-list")
	public String carList(HttpServletRequest request, Model model,
			NavigationItemQO qo) {
		model.addAttribute("show", qo.getId());
		return "/product/car-list.html";
	}

	@RequestMapping(value = "/truck-list")
	public String truckList(HttpServletRequest request, Model model,
			NavigationItemQO qo) {
		NavigationItem navigationItem=null;
		if(StringUtils.isNotBlank(qo.getId())){
			 navigationItem=navigationItemService.queryUnique(qo);
			 model.addAttribute("show", navigationItem.getTitle());
		}else{
			model.addAttribute("show",qo.getProductCatoryName());
		}
		 
		// 获取筛选条件
		productService.getTruck(request, model,qo);
		
		// 获取品牌
		productService.getProductBrand(request, model,qo);
		
		return "/product/truck-list.html";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/detaile")
	public String detaile(HttpServletRequest request, Model model,
			ProductQO productQO) {
		/**
		 * 根据id查找产品
		 * 
		 * 根据产品id查属性
		 * 
		 * 3根据产品id查sku
		 * 
		 * 根据产品id查Item
		 */

		productQO.setFetchMerchant(true);
		Product product = productService.queryUnique(productQO);
		model.addAttribute("product", product);
		
		ImageQO imQO = new ImageQO();
		imQO.setDomainId(product.getMerchant().getId());
		imQO.setDomainType("shop_image");
		List<Image> imageList = imageService.queryList(imQO);
		Map<String,String> mpList=null;
		if(CollectionUtils.isNotEmpty(imageList)){
			mpList=(Map<String, String>) JSON.parse(imageList.get(0).getSpecImageMapJSON());
		}
		
		model.addAttribute("mpList", mpList);

		MerchantContactInfoQO merchantContactInfoQO = new MerchantContactInfoQO();
		merchantContactInfoQO.setMerchantQO(new MerchantQO());
		merchantContactInfoQO.setFetchArea(true);
		merchantContactInfoQO.setFetchCity(true);
		merchantContactInfoQO.setFetchProvince(true);
		merchantContactInfoQO.getMerchantQO().setId(product.getMerchant().getId());
		MerchantContactInfo merchantContactInfo = merchantContactInfoService.queryUnique(merchantContactInfoQO);
		model.addAttribute("merchantContactInfo", merchantContactInfo);

		SkuItemsQO skuItemsQO = new SkuItemsQO();
		skuItemsQO.setOrderBy(1);
		skuItemsQO.setProductQO(new ProductQO());
		skuItemsQO.getProductQO().setId(product.getId());
		List<SkuItems> skuItemsList = skuItemsService.queryList(skuItemsQO);
		model.addAttribute("skuItemsList", skuItemsList);

		ImageQO iqo = new ImageQO();
		iqo.setDomainId(product.getId());
		iqo.setDomainType("product_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String, String> mp = (Map<String, String>) JSON.parse(image.get(0).getSpecImageMapJSON());
		model.addAttribute("imageList", mp);

		ProductStatisticsQO pstQO = new ProductStatisticsQO();
		pstQO.setProductQO(new ProductQO());
		pstQO.getProductQO().setId(product.getId());
		ProductStatistics productStatistics = productStatisticsService
				.queryUnique(pstQO);
		model.addAttribute("pds", productStatistics);

		SKUProductQO sKUProductQO = new SKUProductQO();
		sKUProductQO.setProductQO(new ProductQO());
		sKUProductQO.getProductQO().setId(product.getId());
		List<SKUProduct> sKUProductList = sKUProductService
				.queryList(sKUProductQO);
		Integer skuNumer = 0;
		for (SKUProduct sku : sKUProductList) {
			skuNumer += sku.getNumber();
		}
		model.addAttribute("skuNumer", skuNumer);

		// 外观图片
		ProductExteriorQO productExteriorQO = new ProductExteriorQO();
		productExteriorQO.setProductQO(new ProductQO());
		productExteriorQO.getProductQO().setId(product.getId());
		List<ProductExterior> productExteriorList = productExteriorService
				.queryList(productExteriorQO);

		for (ProductExterior productExterior : productExteriorList) {

			if (StringUtils.equals(productExterior.getType(), "EXTERIOR")) {
				model.addAttribute("exterior",
						productService.getimage(productExterior.getId()));
			}
			if (StringUtils.equals(productExterior.getType(), "DRIVERCAB")) {
				model.addAttribute("driverCab",
						productService.getimage(productExterior.getId()));
			}
			if (StringUtils.equals(productExterior.getType(), "TERRITORY")) {
				model.addAttribute("territory",
						productService.getimage(productExterior.getId()));
			}
		}
		model.addAttribute("show",productQO.getProductCatoryName());
		return "/product/detail.html";
	}

	@RequestMapping(value = "/getBrandDefault")
	@ResponseBody
	public String getBrandDefault(HttpServletRequest request, ProductBrandQO qo) {

		List<ProductBrand> brandList = null;
		if (StringUtils.isNoneBlank(qo.getBrandId())) {
			qo.setOrderBy(1);
			qo.setParentQO(new ProductBrandQO());
			qo.setProductCategoryQO(new ProductCategoryQO());
			qo.getProductCategoryQO().setId(qo.getProductCategoryIds());
			qo.getParentQO().setId(qo.getBrandId());
			brandList = productBrandService.queryList(qo);
		}

		/**
		 * 1 将品牌转换成DTO
		 * 
		 */
		List<ProductBrandDTO> brandDTOList = new ArrayList<ProductBrandDTO>();
		if (CollectionUtils.isNotEmpty(brandList)) {
			for (ProductBrand brand : brandList) {
				if (!StringUtils.equals(brand.getBrandName(), brand.getParent()
						.getBrandName())) {
					ProductBrandDTO brandDTO = new ProductBrandDTO();
					brandDTO.setId(brand.getId());
					brandDTO.setBrandName(brand.getBrandName());
					brandDTO.setSignImage(brand.getSignImage());
					brandDTOList.add(brandDTO);
				}
			}
		}

		return JSONUtils.c(brandDTOList);
	}

	@RequestMapping(value = "/getBrandDefaultProduct")
	@ResponseBody
	public String getBrandDefaultProduct(HttpServletRequest request,
			ProductQO productQO) {
		Pagination pagination = new Pagination();
		PageUtils pageUtils = null;
		if (StringUtils.isNoneBlank(productQO.getBrandId())&&StringUtils.isNotBlank(productQO.getProductCategoryId())) {
			productQO.setStatus(Product.STATUS_ENABLE);

			if (StringUtils.isNotBlank(productQO.getScreeningConditionId())) {
				if (CollectionUtils.isNotEmpty(productService
						.getProductId(productQO))) {
					productQO.setIds(productService.getProductId(productQO));
					return JSONUtils.c(productService.getQueryPagtion(pageUtils, pagination,
							productQO));
				} else {
					pageUtils =PageUtils.getPageNation(pagination);
					return JSONUtils.c(pageUtils);
				}
			} else {
				pageUtils = productService.getQueryPagtion(pageUtils, pagination, productQO);
			}

		}

		return JSONUtils.c(pageUtils);
	}

	

	@RequestMapping(value = "/selectSkuProduct")
	@ResponseBody
	public String selectSkuProduct(HttpServletRequest request,
			SKUProductQO sKUProductQO) {

		SKUProduct sKUProduct = null;
		if (StringUtils.isNotBlank(sKUProductQO.getProductId())) {

			sKUProductQO.setProductQO(new ProductQO());
			sKUProductQO.getProductQO().setId(sKUProductQO.getProductId());
			sKUProduct = sKUProductService.queryUnique(sKUProductQO);
		}
		return JSONUtils.c(sKUProduct);
	}
}
