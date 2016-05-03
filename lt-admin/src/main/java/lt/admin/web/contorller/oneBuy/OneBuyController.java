package lt.admin.web.contorller.oneBuy;

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
import lt.oneBuy.command.CreateOneBuyCommand;
import lt.oneBuy.command.DisableOneBuyCommand;
import lt.oneBuy.command.EnableOneBuyCommand;
import lt.oneBuy.command.ModifyOneBuyCommand;
import lt.oneBuy.dto.OneBuyDTO;
import lt.oneBuy.entity.OneBuy;
import lt.oneBuy.qo.OneBuyQO;
import lt.oneBuy.service.OneBuyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping("oneBuy")
public class OneBuyController extends BaseController{
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(OneBuyController.class);
	
	@Autowired
	private OneBuyService oneBuyService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("")
	public String view(){
		
		return "oneBuy/view.html";
	}
	
	/**
	 * 修改数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(ModifyOneBuyCommand command) {
		try {
			oneBuyService.modify(command);
		} catch (Exception e) {
			logger.error("【编辑异常】OneBuyController.modify(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
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
	public String edit(HttpServletRequest request, Model model, OneBuyQO qo) {
		qo.setFetchCloudBrand(true);
		qo.setFetchCloudCategory(true);
		OneBuy cloundPurchase = oneBuyService.queryUnique(qo);
		model.addAttribute("cloundPurchase",cloundPurchase);

		ImageQO iqo = new ImageQO();
		iqo.setDomainId(cloundPurchase.getId());
		iqo.setDomainType("product_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String,String> mp=(Map<String, String>) JSON.parse(image.get(0).getSpecImageMapJSON());
		model.addAttribute("imageList", mp);
		
		return "/oneBuy/edit.html";
	}
	
	
	/**
	 * 下架
	 * @param request
	 * @param command
	 */
	@RequestMapping("/disable")
	@ResponseBody
	public String disable(HttpServletRequest request, DisableOneBuyCommand command) {

		try {
			oneBuyService.disable(command);
		} catch (Exception e) {
			logger.error( "【 下架异常】PostCotroller.modify(request, command)"
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
	public String enable(HttpServletRequest request, EnableOneBuyCommand command) {

		try {
			oneBuyService.enable(command);
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
			EnableOneBuyCommand command) {

		try {
			oneBuyService.hotT(command);
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
			DisableOneBuyCommand command) {

		try {
			oneBuyService.hotF(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "设置失败");
		}

		return ResultJSON.resultToJSONStr(true, "设置成功");
	}
	
	
	/**
	 * 新增数据
	 * @param command
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(HttpServletRequest request,CreateOneBuyCommand command) {
		try {
			
			oneBuyService.createProduct(command);
			return ResultJSON.resultToJSONStr(true, "一元购产品添加成功");
		} catch (Exception e) {
			logger.error("【新增异常】OneBuyController.create(request, command)" + e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "添加失败，请检查是否有参数漏填了");
		}
		
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Model model,OneBuyDTO oneBuyDTO) {
		OneBuyQO oneBuyQO=new OneBuyQO();
		List<OneBuy> cloundPurchasesList= oneBuyService.queryList(oneBuyQO);
		model.addAttribute("cloundPurchasesList", cloundPurchasesList);
		return "/oneBuy/add.html";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, OneBuyQO qo) {
		Pagination pagination = new Pagination();
	   qo.setNameLike(true);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		
		try {
			pagination = oneBuyService.queryPagination(pagination);
		//	pagination.setList(ProductDTO.domainToDTO((List<CloundPurchase>) pagination.getList()));
		} catch (Exception e) {
			logger.error("【查询异常】OneBuyService.queryPagination(pagination)" + e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}
	
}
