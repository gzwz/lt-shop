package lt.pc.web.contorller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import lt.content.entity.NavigationItem;
import lt.content.qo.NavigationItemQO;
import lt.content.service.NavigationItemService;
import lt.order.entity.ShoppingCar;
import lt.order.qo.ShoppingCarQO;
import lt.order.service.ShoppingCarItemService;
import lt.order.service.ShoppingCarService;
import lt.sitepc.entity.WebSite;
import lt.sitepc.qo.WebSiteQO;
import lt.sitepc.service.WebSiteService;
import lt.user.dto.UserDTO;
import lt.utils.ConverStr;
import lt.utils.CustomStringEditor;
import lt.utils.SessionUtil;

/**
 * 
 * @author
 * 
 */
public class BaseController {

	/**
	 * service注解
	 */
	@Autowired
	private NavigationItemService navigationItemService;

	@Autowired
	private WebSiteService webSiteService;
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	// 初始化导航
	@ModelAttribute
	public void initData(HttpServletRequest request, Model model) {

		// 导航
		getNavigationItem(request, model);

		// 网站设置
		getWebSite(request, model);
		
		getShoppingCarCount(request, model);
	}

	public void getNavigationItem(HttpServletRequest request, Model model) {
		NavigationItemQO qo = new NavigationItemQO();
		qo.setOrderBy(1);
		List<NavigationItem> ngList = navigationItemService.queryList(qo);
		model.addAttribute("show", "1");
		model.addAttribute("ngList", ngList);
	}

	public void getWebSite(HttpServletRequest request, Model model) {
		WebSiteQO webSiteQO = new WebSiteQO();
		List<WebSite> list = webSiteService.queryList(webSiteQO);
		for (WebSite webSite : list) {
			if (StringUtils.equals(webSite.getName(), "站点配置")) {
				model.addAttribute("configName", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "SEO标题")) {
				model.addAttribute("title", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "SEO描述")) {
				model.addAttribute("describ", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "站点名称")) {
				model.addAttribute("siteName", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "二维码")) {
				model.addAttribute("qrCode", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "SEO关键词")) {
				model.addAttribute("key", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "站点版权")) {
				model.addAttribute("copyright", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "系统LOGO")) {
				model.addAttribute("logo", webSite.getValue());
			} else if (StringUtils.equals(webSite.getName(), "网络备案信息")) {
				model.addAttribute("record", webSite.getValue());
			}
		}
		request.getSession().setAttribute("webSiteList", list);
	}
	
	
	public void getShoppingCarCount(HttpServletRequest request, Model model){
		int count=0;
     UserDTO  userDTO = SessionUtil.getLoginUser(request);
		if(null!=userDTO){
			ShoppingCarQO shoppingCarQO=new ShoppingCarQO();
			shoppingCarQO.setFetchShoppingCarItem(true);
			shoppingCarQO.setBatchResult(true);
			shoppingCarQO.setUserId(userDTO.getId());
			List<ShoppingCar> shoppingCars=shoppingCarService.queryList(shoppingCarQO);
			for (ShoppingCar shoppingCar : shoppingCars) {
				count+=shoppingCar.getItems().size();
			}
		}
		
		model.addAttribute("count", count);
	}

	/**
	 * 直接从response输出内容（通常用作ajax返回）
	 */
	protected boolean print(HttpServletResponse response, String msg) {
		try {
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			response.getWriter().print(msg);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	

	
	
	
	/**
	 * 参数格式化转码
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	dateFormat.setLenient(false);
	if ("GET".equalsIgnoreCase(request.getMethod())) {
	binder.registerCustomEditor(String.class, new CustomStringEditor(ConverStr.UTF_8));
	}
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
}
