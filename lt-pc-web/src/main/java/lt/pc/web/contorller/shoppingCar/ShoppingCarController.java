package lt.pc.web.contorller.shoppingCar;


import java.util.List;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;










import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.order.command.CreateShoppingCarCommand;
import lt.order.command.ValidShoppingCarCommand;
import lt.order.entity.ShoppingCar;
import lt.order.entity.ShoppingCarItem;
import lt.order.qo.ShoppingCarQO;
import lt.order.service.ShoppingCarItemService;
import lt.order.service.ShoppingCarService;
import lt.pc.web.contorller.BaseController;
import lt.product.entity.Product;
import lt.product.qo.ProductQO;
import lt.product.service.ProductService;
import lt.user.dto.UserDTO;
import lt.utils.SessionUtil;
@Controller
@RequestMapping(value = "/intercept")
public class ShoppingCarController extends BaseController {
    
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCarItemService shoppingCarItemService;
	
	@RequestMapping(value = "/addShoppingCar")
	@ResponseBody
	public String addShoppingCar(HttpServletRequest request,CreateShoppingCarCommand command){
		UserDTO  userDTO = SessionUtil.getLoginUser(request);
		if(null!=userDTO){
			command.setUserId(userDTO.getId());
		}
		return shoppingCarService.createShoppingCar(command);
	}
	
	@RequestMapping(value = "/shoppingCar-view")
	public String shoppingCarView(HttpServletRequest request,Model model){
		
		
		return "/userCenter/shoppingCar/shoppingCar.html";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajaxQueryShoppingCar")
	@ResponseBody
	public String ajaxQueryShoppingCar(HttpServletRequest request,ShoppingCarQO qo){
		
		/**
		 * 1.检查购物车中的商品在产品表中是否还存在 ，如果不存在 修改该条购物车失效 
		 */
		
		UserDTO  userDTO = SessionUtil.getLoginUser(request);
		Pagination pagination = new Pagination();
		if(null!=userDTO){
			qo.setFetchShoppingCarItem(true);
			qo.setBatchResult(true);
			qo.setUserId(userDTO.getId());
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination =shoppingCarService.queryPagination(pagination);
			List<ShoppingCar> list=(List<ShoppingCar>) pagination.getList();
			for(ShoppingCar shoppingCar:list){
				List<ShoppingCarItem> itemList=shoppingCar.getItems();
				
				for (ShoppingCarItem shoppingCarItem : itemList) {
					ProductQO productQO=new ProductQO();
					productQO.setId(shoppingCarItem.getSkuProductId());
					Product product = productService.queryUnique(productQO);
					if(null==product){
						ValidShoppingCarCommand command=new ValidShoppingCarCommand();
						command.setShoppingCarId(shoppingCar.getId());
						shoppingCarItemService.valid(command);
					}
				}
				
			}
		}
		
		//pagination =shoppingCarService.queryPagination(pagination);
		return JSONUtils.c(pagination);
	}
	
	
	
	@RequestMapping(value = "/deleteShoppingCar")
	@ResponseBody
	public String deleteShoppingCar(HttpServletRequest request,String shoppingCarId){
		
		try {
			
			if(StringUtils.isNotBlank(shoppingCarId)){
				for(int i=0;i<shoppingCarId.split(",").length;i++){
					shoppingCarService.deleteById(shoppingCarId.split(",")[i]);
				}
			}else{
				return ResultJSON.resultToJSONStr(false, "移除购物失败");
			}
			
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "移除购物失败");
		}
		
		return ResultJSON.resultToJSONStr(true, "移除购物成功");
	}
	
	
	@RequestMapping(value = "/deleteShoppingCarItem")
	@ResponseBody
	public String deleteShoppingCarItem(HttpServletRequest request,String shoppingCarItemIds){
		
		try {
			
			if(StringUtils.isNotBlank(shoppingCarItemIds)){
				for(int i=0;i<shoppingCarItemIds.split(",").length;i++){
					shoppingCarItemService.deleteById(shoppingCarItemIds.split(",")[i]);
				}
			}else{
				return ResultJSON.resultToJSONStr(false, "移除购物失败");
			}
			
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "移除购物失败");
		}
		
		return ResultJSON.resultToJSONStr(true, "移除购物成功");
	}
	
}