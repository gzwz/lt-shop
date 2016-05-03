package lt.pc.web.contorller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.order.command.CreateOrderFromShoppingCarCommand;
import lt.order.command.CreateOrderImmediateCommand;
import lt.order.command.ModifyShoppingCarNumCommand;
import lt.order.command.ShoppingItemSubmitCommand;
import lt.order.entity.Order;
import lt.order.entity.OrderConsolidation;
import lt.order.entity.ShoppingCar;
import lt.order.entity.ShoppingCarItem;
import lt.order.exception.OrderException;
import lt.order.qo.ShoppingCarQO;
import lt.order.service.OrderService;
import lt.order.service.ShoppingCarItemService;
import lt.order.service.ShoppingCarService;
import lt.pc.web.contorller.BaseController;
import lt.product.command.SubmitProductCommand;
import lt.product.dto.ProductShopDTO;
import lt.product.dto.ProductShowDTO;
import lt.product.entity.Product;
import lt.product.entity.SKUProduct;
import lt.product.qo.ProductQO;
import lt.product.qo.SKUProductQO;
import lt.product.service.ProductService;
import lt.product.service.SKUProductService;
import lt.user.dto.UserDTO;
import lt.user.service.AddressesService;
import lt.user.service.UserService;
import lt.utils.SessionUtil;
/**
 * 2016-04-20
 * @author wxp
 * 
 *
 */
@Controller
@RequestMapping(value = "/intercept")
public class OrderController extends BaseController{
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressesService addrService;
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	@Autowired
	private ShoppingCarItemService shoppingCarItemService;
	
	@Autowired
	private SKUProductService sKUProductService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/fill-information")
	public String fillInformation(HttpServletRequest request,Model model,SubmitProductCommand command){
		
		ProductShowDTO productShowDTO=new ProductShowDTO();
		
		SKUProductQO sKUProductQO=new SKUProductQO();
		sKUProductQO.setProductQO(new ProductQO());
		sKUProductQO.getProductQO().setId(command.getProductId());
		sKUProductQO.setSkuInfo(command.getSkuSpecInfo());
		SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);
		if(null!=sKUProduct){
			productShowDTO.setSkuPrice(Double.parseDouble(String.format("%.2f", sKUProduct.getPrice())));
			productShowDTO.setSkuSpecInfo(sKUProduct.getSkuSpecInfo());
		}
		double totale=0;
		ProductQO productQO=new ProductQO();
		productQO.setFetchProductBrand(true);
		productQO.setFetchMerchant(true);
		productQO.setId(sKUProduct.getProduct().getId());
		Product product = productService.queryUnique(productQO);
		productShowDTO.setMerchantName(product.getMerchant().getBaseInfo().getName());
		productShowDTO.setMerchantId(product.getMerchant().getId());
		
		productShowDTO.setProductName(product.getShowInfo().getName());
		productShowDTO.setBrandName(product.getProductBrand().getBrandName());
		productShowDTO.setUrlImage(product.getShowInfo().getTitleImage());
		productShowDTO.setProductCatoryName(command.getProductCatoryName());
		productShowDTO.setProductId(sKUProduct.getProduct().getId());
		productShowDTO.setId(sKUProduct.getProduct().getId());
		productShowDTO.setNum(command.getNum());
		totale=command.getNum()*sKUProduct.getPrice();
		if(command.getPrice()==totale){
			model.addAttribute("totalePrice",totale);
		}else{
			model.addAttribute("totalePrice",totale);
		}
		 model.addAttribute("show",productShowDTO);
		return "/order/product-order/fill-information.html";
	}
	
	@RequestMapping(value = "/fill-information-car")
	public String shoppingCarView(HttpServletRequest request,Model model,ShoppingItemSubmitCommand commandSub){
		
		List<String> shopCList=new ArrayList<String>();
		List<ProductShowDTO> productShowDTOList=new ArrayList<ProductShowDTO>();
		Double totaleP=0.00;
		try {
			/**
			 * 1.更新购物车的数量
			 * 
			 */
			for (int i = 0; i < commandSub.getShoppingCarItems().split(",").length; i++) {
				
				ModifyShoppingCarNumCommand command=new ModifyShoppingCarNumCommand();
				command.setShoppingCarId(commandSub.getShoppingCarItems().split(",")[i]);
				command.setNum(Integer.parseInt(commandSub.getShoppingNume().split(",")[i]));
				ShoppingCarItem shoppingCarItem = shoppingCarItemService.modifyNum(command);
				shopCList.add(shoppingCarItem.getShoppingCar().getId());
				/**
				 * 1.根据购车中的产品Id和参数 查找sku信息 防止当前购物车的价格和sku里的不同
				 * 
				 * 2.跟据商品id查看产品信息和品牌名
				 */
				SKUProductQO sKUProductQO=new SKUProductQO();
				sKUProductQO.setProductQO(new ProductQO());
				sKUProductQO.getProductQO().setId(shoppingCarItem.getSkuProductId());
				sKUProductQO.setSkuInfo(shoppingCarItem.getSkuSpecInfo());
				SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);
				
				//取sku来计算的价格
				totaleP+=sKUProduct.getPrice()*shoppingCarItem.getNum();
				
				ProductShowDTO productShowDTO=new ProductShowDTO();
				productShowDTO.setSkuPrice(Double.parseDouble(String.format("%.2f", sKUProduct.getPrice())));
				productShowDTO.setSkuSpecInfo(sKUProduct.getSkuSpecInfo());
				
				ProductQO productQO=new ProductQO();
				productQO.setFetchProductBrand(true);
				productQO.setFetchMerchant(true);
				productQO.setId(sKUProduct.getProduct().getId());
				Product product = productService.queryUnique(productQO);
				productShowDTO.setMerchantName(product.getMerchant().getBaseInfo().getName());
				productShowDTO.setProductName(product.getShowInfo().getName());
				productShowDTO.setBrandName(product.getProductBrand().getBrandName());
				productShowDTO.setUrlImage(shoppingCarItem.getTitleImageUrl());
				productShowDTO.setProductCatoryName(shoppingCarItem.getProductCatoryName());
				productShowDTO.setProductId(shoppingCarItem.getSkuProductId());
				productShowDTO.setNum(shoppingCarItem.getNum());
				productShowDTO.setId(shoppingCarItem.getId());
				productShowDTOList.add(productShowDTO);
				
			}
			
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			 return "/exception/orderException.html";
		}
		
		shopCList=psiqList(shopCList);
		List<ProductShopDTO> productShopDTOList=new ArrayList<ProductShopDTO>();
		for (String sc : shopCList) {
			ProductShopDTO dto=new ProductShopDTO();
			ShoppingCarQO qo=new ShoppingCarQO();
			qo.setFetchShoppingCarItem(true);
			qo.setBatchResult(true);
			qo.setId(sc);
			ShoppingCar shoppingCar =shoppingCarService.queryUnique(qo);
			dto.setId(shoppingCar.getMarketingInfo().getOperationMerchantId());
			dto.setMerchantName(shoppingCar.getMarketingInfo().getOperationMerchantName());
			List<ProductShowDTO> productShowList=new ArrayList<ProductShowDTO>();
			for(ShoppingCarItem item:shoppingCar.getItems()){
				for(ProductShowDTO pdt:productShowDTOList){
					if(StringUtils.equals(item.getId(), pdt.getId())){
						productShowList.add(pdt);
					}
				}
			}
			dto.setShowItem(productShowList);
			productShopDTOList.add(dto);
		}
		
		
		if(Double.parseDouble(String.format("%.2f", totaleP))==Double.parseDouble(commandSub.getTotale())){
			model.addAttribute("totalePrice",Double.parseDouble(String.format("%.2f", totaleP)));
		}else{
			model.addAttribute("totalePrice",Double.parseDouble(String.format("%.2f", totaleP)));
		}
		    model.addAttribute("showDTOList",productShopDTOList);
		return "/order/shoppingCar-order/fill-information.html";
	}
	
	
	// 去重
		public List<String> psiqList(List<String> psiList) {
			for (int i = 0; i < psiList.size(); i++) {
				for (int j = psiList.size() - 1; j > i; j--) {
					if (psiList.get(j).equals(psiList.get(i))) {
						psiList.remove(j);
					}
				}
			}
			return psiList;
		}
	
	/**
	 * 立即支付
	 * @param request
	 * @param model
	 * @param command
	 * @return
	 * @throws OrderException
	 */
	@RequestMapping(value = "/immd-pay-way")
	public String immdPayWay(HttpServletRequest request,Model model,CreateOrderImmediateCommand command) throws OrderException{
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		command.setUserId(userDTO.getId());
		 Order order=null;
		try {
			order=orderService.createOrderImmediate(command);
		} catch (OrderException e) {
			 model.addAttribute("exception", e.getMessage());
			 return "/exception/orderException.html";
		}
		 model.addAttribute("order", order);
		return "/order/pay.html";
	}
	
	/**
	 * 购物车支付
	 * @param request
	 * @param model
	 * @param command
	 * @return
	 * @throws OrderException
	 */
	@RequestMapping(value = "/select-pay-way")
	public String selectPayWay(HttpServletRequest request,Model model,CreateOrderFromShoppingCarCommand command) throws OrderException{
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		command.setUserId(userDTO.getId());
		OrderConsolidation sc=null;
		 String[] merchantIds = request.getParameterValues("merchantIds");
		 String[] shoppingCarItemIds = request.getParameterValues("shoppingCarItemIds");
			
		try {
			sc=orderService.createOrderFromShoppingCar(command, merchantIds, shoppingCarItemIds);
			orderService.deleteShoppingCar(merchantIds);
		} catch (OrderException e) {
			 model.addAttribute("exception", e.getMessage());
			 return "/exception/orderException.html";
		}
		 model.addAttribute("sc", sc);
		 return "/order/shoppingCar-order/select-pay-way.html";
	}
	
	
	@RequestMapping(value = "/pay")
	public String pay(HttpServletRequest request,Model model){
		
		return "/order/product-order/pay.html";
	}
	
	
	@RequestMapping(value = "/pay-success")
	public String paySuccess(HttpServletRequest request,Model model){
		
		return "/order/product-order/pay-success.html";
	}

}
