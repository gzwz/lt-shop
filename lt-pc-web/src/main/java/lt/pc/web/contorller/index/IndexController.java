package lt.pc.web.contorller.index;


import javax.servlet.http.HttpServletRequest;

import lt.ad.service.AdService;
import lt.articlecate.service.ArticleCateService;
import lt.base.service.BannerService;
import lt.content.qo.NavigationItemQO;
import lt.merchant.entity.Merchant;
import lt.merchant.entity.MerchantContactInfo;
import lt.merchant.qo.MerchantContactInfoQO;
import lt.merchant.qo.MerchantQO;
import lt.merchant.service.MerchantContactInfoService;
import lt.merchant.service.MerchantService;
import lt.oneBuy.service.OneBuyService;
import lt.pc.web.contorller.BaseController;
import lt.product.service.ProductBrandService;
import lt.product.service.ProductCategoryService;
import lt.product.service.ScreeningConditionService;
import lt.sitepc.service.FloorsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private AdService adService;

	/**
	 * service注解
	 */
	@Autowired
	private BannerService bannerService;

	@Autowired
	private OneBuyService oneBuyService;

	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;

	/** service */
	@Autowired
	private ProductBrandService productBrandService;

	/** service */
	@Autowired
	private ScreeningConditionService screeningConditionService;
	
	/** 店铺service */
	@Autowired
	private MerchantService merchantService;

	@Autowired
	private FloorsService floorsService;
	
	@Autowired
	private MerchantContactInfoService merchantContactInfoService;
	
	@Autowired
	private ArticleCateService articleCateService;
	
	@RequestMapping(value = "/query/maps")
	public String queryMaps(HttpServletRequest request, Model model,MerchantQO merchantQO) {
		
		merchantQO.setFetchMerchantCategory(true);
		Merchant merchant=merchantService.queryUnique(merchantQO);
		model.addAttribute("merchant", merchant);
		
		MerchantContactInfoQO merchantContactInfoQO=new MerchantContactInfoQO();
		merchantContactInfoQO.setFetchArea(true);
		merchantContactInfoQO.setFetchCity(true);
		merchantContactInfoQO.setFetchProvince(true);
		merchantContactInfoQO.setMerchantQO(new MerchantQO());
		merchantContactInfoQO.getMerchantQO().setId(merchant.getId());
		MerchantContactInfo merchantContactInfo=merchantContactInfoService.queryUnique(merchantContactInfoQO);
		model.addAttribute("merchantContactInfo", merchantContactInfo);
		
		return "/common/map.html";
	}


	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model,
			NavigationItemQO qo) {

		// 广告
		adService.getAd(request, model);
		// banner
		bannerService.getFlag(request, model);
		// 一元购
		oneBuyService.getOneBuy(request, model);
		// 分类
		productCategoryService.getProductCatory(request, model);
		// 价格
		screeningConditionService.getScreeningCondition(request, model);
		// 品牌
		productBrandService.getProductBrand(request, model);
		// 楼层
		floorsService.getFloors(request, model);
		//新闻中心
		articleCateService.getAirticleCate(request, model);
        //判断是否选中状态
		model.addAttribute("show", "1");
		return "/index.html";
	}


}
