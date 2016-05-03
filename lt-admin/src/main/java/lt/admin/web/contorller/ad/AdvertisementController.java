package lt.admin.web.contorller.ad;


import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.ad.command.AdCommand;
import lt.ad.command.ChangeCommand;
import lt.ad.entity.Advertisement;
import lt.ad.qo.AdQO;
import lt.ad.service.AdService;
import lt.admin.dto.AdminDTO;
import lt.admin.web.contorller.BaseController;
import lt.admin.web.contorller.oneBuy.OneBuyController;
import lt.user.command.ChangeUserCommand;
import lt.utils.RESULT;
import lt.utils.SessionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("adListView")
public class AdvertisementController extends BaseController{
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OneBuyController.class);
	
	@Autowired
	private AdService adService;

	@RequestMapping()
	public String init(){
		
		return "/advertisement/view.html";
	}
	
	@RequestMapping("edit")
	public String edit(AdQO adQO,Model model){
		Advertisement advertisement = adService.queryUnique(adQO);
		model.addAttribute(advertisement);
		return "/advertisement/edit.html";
	}
	
	
	@ResponseBody
	@RequestMapping("editSave")
	public String editSave(HttpServletRequest request, AdCommand adCommand){
		//获取用户id 
		AdminDTO dto = SessionUtil.getLoginAdmin(request);
			
			RESULT result=adService.editSave(adCommand,dto.getId());
			if (result ==RESULT.SUCCESS) {
				return ResultJSON.resultToJSONStr(true, "添加成功");
			}else {
				return ResultJSON.resultToJSONStr(false, "添加失败");
			}
		
	}
	
	
	@RequestMapping("add")
	public String add(){
		
		return "/advertisement/add.html";
	}
	
	@ResponseBody
	@RequestMapping("/change")
	public String change(ChangeCommand command) {
		try {
			adService.change(command);
			
			return ResultJSON.resultToJSONStr(true, "操作成功");	
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "操作失败");	
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public String query(HttpServletRequest request, AdQO qo) {
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		
		try {
			pagination = adService.queryPagination(pagination);
			
		//	pagination.setList(ProductDTO.domainToDTO((List<CloundPurchase>) pagination.getList()));
		} catch (Exception e) {
			logger.error("【查询异常】AdService.queryPagination(pagination)" + e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}
	
	@ResponseBody
	@RequestMapping("save")
	public String save(HttpServletRequest request, AdCommand adCommand){
		//获取用户id 
		AdminDTO dto = SessionUtil.getLoginAdmin(request);
		try {
			adService.add(adCommand,dto.getId());
			return ResultJSON.resultToJSONStr(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "添加失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String del(String id){
		//获取用户id 
		try {
			adService.del(id);
			return ResultJSON.resultToJSONStr(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}
		
	}
	
	@RequestMapping("look")
	public String look(HttpServletRequest request,String id){
		//获取用户id 
		Advertisement advertisement = null ;
		try {
			advertisement = adService.look(id);
			request.setAttribute("ad", advertisement);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("【查询异常】AdService.look(id)" + e.getMessage(), e);
		}
		return "/advertisement/detail.html";
	}
	
}
