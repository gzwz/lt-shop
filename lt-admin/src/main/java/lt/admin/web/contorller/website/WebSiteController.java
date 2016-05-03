package lt.admin.web.contorller.website;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.admin.web.contorller.BaseController;
import lt.sitepc.command.CreateWebSiteCommand;
import lt.sitepc.command.ModifyWebSiteCommand;
import lt.sitepc.entity.WebSite;
import lt.sitepc.qo.WebSiteQO;
import lt.sitepc.service.WebSiteService;
/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/website")
public class WebSiteController extends BaseController {
	
	@Autowired
	private WebSiteService webSiteService;
	
	@RequestMapping("/view")
	public String webSite(HttpServletRequest request,CreateWebSiteCommand command,Model model,WebSiteQO qo){
		List<WebSite> list=null;
		try {
			webSiteService.createWebSite(command);
			list=webSiteService.queryList(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("websiteList", list);
		return "/webSite/view.html";
	}
	
	@RequestMapping("/uporsa")
	public String updateOrSave(HttpServletRequest request,ModifyWebSiteCommand command,Model model,WebSiteQO qo){
		List<WebSite> list=null;
		String[] webSiteIds = request.getParameterValues("webSiteIds");
		String[] webSiteName = request.getParameterValues("webSiteName");
		try {
			for(int i = 0; i<webSiteIds.length; i++){
				command.setWebSiteId(webSiteIds[i]);
				command.setValue(webSiteName[i]);
				webSiteService.modifyWeb(command);
			}
			
			list=webSiteService.queryList(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("websiteList", list);
		return "/webSite/view.html";
	}

}
