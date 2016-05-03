package lt.pc.web.contorller.order;

import java.io.IOException;
import java.util.List;

import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.oneBuy.entity.OneBuy;
import lt.oneBuy.qo.OneBuyQO;
import lt.order.command.CreateorderFromOneBuyCommand;
import lt.order.command.OrderValidateCommand;
import lt.order.entity.Order;
import lt.order.qo.OrderQO;
import lt.order.service.OrderService;
import lt.pc.web.contorller.BaseController;
import lt.user.dto.UserDTO;
import lt.user.entity.User;
import lt.user.entity.UserAddress;
import lt.user.qo.AddressesQO;
import lt.user.qo.UserQO;
import lt.user.service.AddressesService;
import lt.user.service.UserService;
import lt.utils.OrderUtil;
import lt.utils.SessionUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/oneBuyOrder")
public class OneBuyOrderController extends BaseController{
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressesService addrService;
	
	@ResponseBody
	@RequestMapping("/createOrderInfo")
	public String validate(HttpServletRequest request, HttpServletResponse response,CreateorderFromOneBuyCommand buyCommand) throws IOException{
		UserDTO  userDTO = SessionUtil.getLoginUser(request);
		if (!userDTO.getId().equals(buyCommand.getUserId())) {
			return ResultJSON.resultToJSONStr(false, "当前用户与该订单不匹配");
		}
	return	orderService.validate(request,buyCommand,userDTO);
		
	//	return ResultJSON.resultToJSONStr(true, "恭喜验证成功");
	}

	/**
	 * 
	 * @param request
	 * @param oneBuyQO
	 * @return 确认订单详情
	 */
	@RequestMapping("confirmOrder")
	public String getOrder(HttpServletRequest request,OneBuyQO oneBuyQO,Integer num){
		//收货地址
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		AddressesQO addressesQO=new AddressesQO();
		addressesQO.setFetchArea(true);
		addressesQO.setFetchCity(true);
		addressesQO.setFetchProvince(true);
		if(null!=userDTO){
			addressesQO.setUserQO(new UserQO());
			addressesQO.getUserQO().setId(userDTO.getId());
		}
		List<UserAddress> addressesList=addrService.queryList(addressesQO);
		
		request.setAttribute("addrList", addressesList);
		
		//获取当前登录用户(检查用户用是否登录)
		User user = userService.get(userDTO.getId());;
		//获取商品
		OneBuy oneBuy=orderService.oneBuyOrder(request, oneBuyQO);
		//生产订单
		request.setAttribute("outTradeNo", OrderUtil.getOrderId());
		request.setAttribute("user", user);
		request.setAttribute("oneBuy", oneBuy);
		request.setAttribute("num", num);
		
		return "order/oneBuyConfirm.html";
	}
	@RequestMapping("orderToPay")
	public String orderToPay(HttpServletRequest request,OrderQO orderQO){
		System.out.println(orderQO);
		
		if(StringUtils.isNotBlank(orderQO.getOrderId())){
			orderQO.setId(orderQO.getOrderId());
		}
		orderQO.setFetchOrderSkuItems(true);
		orderQO.setFetchBuyer(true);
		Order order = orderService.queryUnique(orderQO);
		request.setAttribute("order", order);
		return "order/pay.html";
	}
	
	@ResponseBody
	@RequestMapping("orderValdate")
	public String valid(OrderValidateCommand orderValidata){
		Order order = null;
		if (StringUtils.isNotBlank(orderValidata.getOutTradeNo())) {
			order = orderService.get(orderValidata.getOutTradeNo());
			if (order.getBaseInfo().getTotalPrice().equals(orderValidata.getTotalFee())) {
				return ResultJSON.resultToJSONStr(true, "订单状态正常");
			}
		}else
		if (StringUtils.isNotBlank(orderValidata.getWIDout_trade_no())) {
			order = orderService.get(orderValidata.getWIDout_trade_no());
			if (order.getBaseInfo().getTotalPrice().equals(orderValidata.getWIDtotal_fee())) {
				return ResultJSON.resultToJSONStr(true, "订单状态正常");
			}
		}
		return ResultJSON.resultToJSONStr(false, "订单状态异常");
		
	}
	
	
	
}
