package lt.admin.web.contorller.order;


import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.admin.web.contorller.BaseController;
import lt.order.entity.Order;
import lt.order.entity.OrderBuyerInfo;
import lt.order.qo.OrderBuyerInfoQO;
import lt.order.qo.OrderQO;
import lt.order.service.OrderBuyerInfoService;
import lt.order.service.OrderService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderBuyerInfoService buyerService;
	
	@RequestMapping("")
	public String view (){
		
		return "/order/view.html";
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public String query(HttpServletRequest request, OrderQO qo) {
		Pagination pagination = new Pagination();
		if(StringUtils.isNotBlank(qo.getOrderId())){
			qo.setId(qo.getOrderId());
		}
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = orderService.queryPagination(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pagination);
	}
	
	@RequestMapping("/item")
	public String checkItem(HttpServletRequest request,HttpServletResponse response,OrderQO qo, Model model)   {
		
		Order order = null;
		OrderBuyerInfo buyerInfo = null;
		OrderBuyerInfoQO buyerInfoQO = new OrderBuyerInfoQO();
		try {
			// 县查出商品的基本信息
			qo.setBatchResult(true);
			qo.setFetchOrderSkuItems(true);
			qo.setFetchBuyer(true);
			order = orderService.queryUnique(qo);
			// 再查出 买家的信息包括地址
			buyerInfoQO.setOrderqo(qo);
			buyerInfoQO.setBatchResult(true);
			buyerInfoQO.setFetchProvince(true);
			buyerInfoQO.setFetchCity(true);
			buyerInfoQO.setFetchArea(true);
			buyerInfo = buyerService.queryUnique(buyerInfoQO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("buyerInfo", buyerInfo);
		model.addAttribute("order", order);
		return "/order/item.html";
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
