package lt.admin.web.contorller.merchant;

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
import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
import lt.merchant.entity.Merchant;
import lt.merchant.entity.MerchantAuthenticateInfo;
import lt.merchant.entity.MerchantContactInfo;
import lt.merchant.qo.MerchantAuthenticateInfoQO;
import lt.merchant.qo.MerchantContactInfoQO;
import lt.merchant.qo.MerchantQO;
import lt.merchant.service.MerchantAuthenticateInfoService;
import lt.merchant.service.MerchantContactInfoService;
import lt.merchant.service.MerchantService;
import lt.user.entity.UserStatus;
import lt.user.service.UserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 店铺
 * 
 * @author wxp  
 */
@Controller
@RequestMapping(value = "/merchant")
public class MerchantController extends BaseController{
	
	/*用户Service*/
	@Autowired
	private UserService userService;
	
	/** 店铺service */
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private MerchantAuthenticateInfoService merchantAuthenticateInfoService;
	
	@Autowired
	private MerchantContactInfoService merchantContactInfoService;
	
	/**
	 * 跳转店铺页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		
		return "/merchant/view.html";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, MerchantQO qo) {
		Pagination pagination = new Pagination();
		qo.setNameLike(true);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = merchantService.queryPagination(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pagination);
	}
	
	
	
	@RequestMapping("/query_authen")
	@ResponseBody
	public String queryAuthen(HttpServletRequest request, MerchantQO qo) {
		qo.setDomainType(UserStatus.VALIDATE_PASS);
		Pagination pagination = new Pagination();
		qo.setNameLike(true);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = merchantService.queryPagination(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pagination);
	}
	/**
	 * wz
	 * @return
	 * 后台 增加 店铺
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/addView")
	public String addView(HttpServletRequest request,MerchantQO merchantQO,Model model){
		
		merchantQO.setFetchMerchantCategory(true);
		Merchant merchant=merchantService.queryUnique(merchantQO);
		model.addAttribute("merchant", merchant);
		
		ImageQO iqo = new ImageQO();
		iqo.setDomainId(merchant.getId());
		iqo.setDomainType("shop_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String,String> mp=null;
		if(CollectionUtils.isNotEmpty(image)){
			 mp=(Map<String, String>) JSON.parse(image.get(0).getSpecImageMapJSON());
		}
		
		model.addAttribute("imageList", mp);
		
		MerchantAuthenticateInfoQO merchantAuthenticateInfoQO=new MerchantAuthenticateInfoQO();
		merchantAuthenticateInfoQO.setMerchantQO(new MerchantQO());
		merchantAuthenticateInfoQO.getMerchantQO().setId(merchant.getId());
		MerchantAuthenticateInfo info=merchantAuthenticateInfoService.queryUnique(merchantAuthenticateInfoQO);
		model.addAttribute("info", info);
		
		MerchantContactInfoQO merchantContactInfoQO=new MerchantContactInfoQO();
		merchantContactInfoQO.setFetchArea(true);
		merchantContactInfoQO.setFetchCity(true);
		merchantContactInfoQO.setFetchProvince(true);
		merchantContactInfoQO.setMerchantQO(new MerchantQO());
		merchantContactInfoQO.getMerchantQO().setId(merchant.getId());
		MerchantContactInfo merchantContactInfo=merchantContactInfoService.queryUnique(merchantContactInfoQO);
		model.addAttribute("merchantContactInfo", merchantContactInfo);
		
		return "/merchant/edit.html";
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(ModifyMerchantCommand command) {
		try {
			merchantService.modifyMerchant(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
	}
	
	
	@RequestMapping("/edit")
	public String merchantD(HttpServletRequest request,MerchantQO qo,Model model){
		
		
		return "/merchant/edit.html";
	}
	/**
	 * wz
	 * @return
	 * 后台 增加 店铺
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addView(CreateMerchantCommand command){
		try {
			merchantService.createMerchant(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "提交失败");
		}
		
		return ResultJSON.resultToJSONStr(true, "提交成功");
	}
	
	@ResponseBody
	@RequestMapping("/check")
	public String check(String sign,String id){
		if (StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(sign)) {
			try {
			
			merchantService.modityStatus(id,sign);
			
			return JSONUtils.c("success");
			} catch (Exception e) {
			return JSONUtils.c("fail");
			}
		}
		return JSONUtils.c("success");
	}
	
	/**
	 * 
	 * @param sign 此方法里面的sign 代表次商家通过验证
	 * @param id
	 * @return
	 * 	变量：	validateShop
	 * 	可选参数为：
	 *  validate_comm	// 消费者
	 *  validate_apply  // 申请中
	 *  validate_verify  // 审核中
	 *  validate_pass	// 审核通过
	 *  validate_unpass // 审核不通过
	 *  
	 *  次方法暂时默认 sign = validate_pass	 // 审核通过
	 */
	@ResponseBody
	@RequestMapping("/checkAll")
	public String checkAll(String sign,String id){
		sign = "validate_pass"; //*****************
		if (StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(sign)) {
			try {
			merchantService.modityStatus(id,sign);
			return ResultJSON.resultToJSONStr(true, "审核通过");
			} catch (Exception e) {
				return ResultJSON.resultToJSONStr(false, "审核失败");
			}
		}
		return ResultJSON.resultToJSONStr(false, "审核失败");
	}
	
	
	@RequestMapping("/merchant-detaile")
	public String merchantDetaile(HttpServletRequest request,MerchantQO qo,Model model){
		Merchant merchant=null;
		MerchantAuthenticateInfo merchantAuthenticateInfo=null;
		MerchantContactInfo merchantContactInfo=null;
		try {
			
			merchant= merchantService.queryUnique(qo);
			 
			MerchantAuthenticateInfoQO merchantAuthenticateInfoQO=new MerchantAuthenticateInfoQO();
			merchantAuthenticateInfoQO.setMerchantQO(new MerchantQO());
			merchantAuthenticateInfoQO.getMerchantQO().setId(merchant.getId());
			merchantAuthenticateInfo=merchantAuthenticateInfoService.queryUnique(merchantAuthenticateInfoQO);
			
			MerchantContactInfoQO merchantContactInfoQO=new MerchantContactInfoQO();
			merchantContactInfoQO.setFetchProvince(true);
			merchantContactInfoQO.setFetchCity(true);
			merchantContactInfoQO.setFetchArea(true);
			merchantContactInfoQO.setMerchantQO(new MerchantQO());
			merchantContactInfoQO.getMerchantQO().setId(merchant.getId());
			merchantContactInfo=merchantContactInfoService.queryUnique(merchantContactInfoQO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("merchant", merchant);
		model.addAttribute("merchantAuthenticateInfo", merchantAuthenticateInfo);
		model.addAttribute("merchantContactInfo", merchantContactInfo);
		return "/merchant/detail.html";
	}
	
	@RequestMapping("/merchant_check")
	public String merchant_check(HttpServletRequest request,MerchantQO qo,Model model){
		Merchant merchant=null;
		MerchantAuthenticateInfo merchantAuthenticateInfo=null;
		MerchantContactInfo merchantContactInfo=null;
		try {
			
			merchant= merchantService.queryUnique(qo);
			
			MerchantAuthenticateInfoQO merchantAuthenticateInfoQO=new MerchantAuthenticateInfoQO();
			merchantAuthenticateInfoQO.setMerchantQO(new MerchantQO());
			merchantAuthenticateInfoQO.getMerchantQO().setId(merchant.getId());
			merchantAuthenticateInfo=merchantAuthenticateInfoService.queryUnique(merchantAuthenticateInfoQO);
			
			MerchantContactInfoQO merchantContactInfoQO=new MerchantContactInfoQO();
			merchantContactInfoQO.setFetchProvince(true);
			merchantContactInfoQO.setFetchCity(true);
			merchantContactInfoQO.setFetchArea(true);
			merchantContactInfoQO.setMerchantQO(new MerchantQO());
			merchantContactInfoQO.getMerchantQO().setId(merchant.getId());
			merchantContactInfo=merchantContactInfoService.queryUnique(merchantContactInfoQO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("merchant", merchant);
		model.addAttribute("mAImgInfo", merchantAuthenticateInfo);
		model.addAttribute("merchantContactInfo", merchantContactInfo);
		return "/merchant/check.html";
	}
	
	
	@RequestMapping("/authentication")
	public String authentication(){
		return "/merchant/authentication.html";
	}
	
	@RequestMapping("/getMerchant")
	@ResponseBody
	public String getMerchant(MerchantQO qo){
		List<Merchant> merchantList=null;
		try {
			merchantList= merchantService.queryList(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(merchantList);
	}
	

}
