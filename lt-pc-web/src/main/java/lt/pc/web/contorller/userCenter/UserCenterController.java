package lt.pc.web.contorller.userCenter;

import java.util.List;

import gzlazypack.common.page.Pagination;










import javax.servlet.http.HttpServletRequest;

import lt.order.entity.OrderBuyerInfo;
import lt.order.qo.OrderBuyerInfoQO;
import lt.order.qo.OrderQO;
import lt.order.service.OrderBuyerInfoService;
import lt.order.service.OrderService;
import lt.pc.web.contorller.BaseController;
import lt.user.dto.UserDTO;
import lt.user.entity.User;
import lt.user.service.UserService;
import lt.utils.SessionUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intercept/userCenter")
public class UserCenterController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderBuyerInfoService orderBuyerInfoService;

	//首页
	@RequestMapping("")
	public String init(HttpServletRequest request, OrderQO orderQO,String currentValue,Integer page){
		if (page == null || page == 0) {
			page = 1;
		}
		//订单
		Pagination pagination = new Pagination();
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		OrderBuyerInfoQO orderBuyerInfoQO=new OrderBuyerInfoQO();
		
		if(StringUtils.isNotBlank(userDTO.getId())){
			orderBuyerInfoQO.setOrderqo(new OrderQO());
			orderBuyerInfoQO.getOrderqo().setFetchOrderSkuItems(true);
			orderBuyerInfoQO.getOrderqo().setBatchResult(true);
			orderBuyerInfoQO.setBuyerUserId(userDTO.getId());
			if(StringUtils.isNotBlank(currentValue)){
				orderBuyerInfoQO.getOrderqo().setCurrentValue(currentValue);
			}
			pagination.setPageNo(orderQO.getPageNo());
			pagination.setPageSize(orderQO.getPageSize());
			pagination.setCondition(orderBuyerInfoQO);
			pagination=orderBuyerInfoService.queryPagination(pagination);
		}
		
		request.setAttribute("pageData", pagination);
		
		return "userCenter/uCenter.html";
	}
	
	//订单
	@RequestMapping("/my_order")
	public String myOrder(HttpServletRequest request, OrderQO orderQO,String currentValue,Integer page){
		if (page == null || page == 0) {
			page = 1;
		}
		//订单
		Pagination pagination = new Pagination();
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		OrderBuyerInfoQO orderBuyerInfoQO=new OrderBuyerInfoQO();
		
		if(StringUtils.isNotBlank(userDTO.getId())){
			orderBuyerInfoQO.setOrderqo(new OrderQO());
			orderBuyerInfoQO.getOrderqo().setFetchOrderSkuItems(true);
			orderBuyerInfoQO.getOrderqo().setBatchResult(true);
			orderBuyerInfoQO.setBuyerUserId(userDTO.getId());
			if(StringUtils.isNotBlank(currentValue)){
				orderBuyerInfoQO.getOrderqo().setCurrentValue(currentValue);
			}
			pagination.setPageNo(page);
			pagination.setPageSize(5);
			pagination.setCondition(orderBuyerInfoQO);
			pagination=orderBuyerInfoService.queryPagination(pagination);
		}
		
		request.setAttribute("pageData", pagination);
		return "userCenter/uCenter.html";
	}
	
	
	@RequestMapping("/my_evaluation")
	public String my_evaluation(){
		
		return "userCenter/my_evaluation.html";
	}
	
	//收藏
	@RequestMapping("/my_collection")
	public String my_collection(){
		
		return "userCenter/collect.html";
	}
}
