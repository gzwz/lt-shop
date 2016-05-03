package lt.order.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import gzlazypack.common.component.BaseDao;
import gzlazypack.common.component.BaseQO;
import gzlazypack.common.util.MoneyUtil;
import gzlazypack.common.util.ResultJSON;
import lt.base.service.AreaService;
import lt.base.service.CityService;
import lt.base.service.ImageService;
import lt.base.service.ProvinceService;
import lt.merchant.entity.Merchant;
import lt.merchant.service.MerchantService;
import lt.oneBuy.entity.LuckNum;
import lt.oneBuy.entity.OneBuy;
import lt.oneBuy.qo.LuckNumQO;
import lt.oneBuy.qo.OneBuyQO;
import lt.oneBuy.service.LuckNumService;
import lt.oneBuy.service.OneBuyService;
import lt.order.command.CreateOrderConsolidationCommand;
import lt.order.command.CreateOrderFromShoppingCarCommand;
import lt.order.command.CreateOrderImmediateCommand;
import lt.order.command.CreateorderFromOneBuyCommand;
import lt.order.entity.Order;
import lt.order.entity.OrderBuyerInfo;
import lt.order.entity.OrderConsolidation;
import lt.order.entity.OrderSKUItem;
import lt.order.entity.ShoppingCar;
import lt.order.entity.ShoppingCarItem;
import lt.order.exception.OrderException;
import lt.order.qo.OrderQO;
import lt.order.qo.ShoppingCarQO;
import lt.pay.alipay.entity.NotifyData;
import lt.pay.alipay.service.NotifyService;
import lt.product.command.AddNumCommand;
import lt.product.entity.Product;
import lt.product.entity.ProductSnapshot;
import lt.product.entity.SKUProduct;
import lt.product.qo.ProductQO;
import lt.product.qo.ProductSnapshotQO;
import lt.product.qo.SKUProductQO;
import lt.product.service.ProductService;
import lt.product.service.ProductSnapshotService;
import lt.product.service.SKUProductService;
import lt.user.dto.UserDTO;
import lt.user.entity.User;
import lt.user.entity.UserAddress;
import lt.user.service.AddressesService;
import lt.user.service.UserService;
import lt.utils.RESULT;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService extends BaseDao<Order, OrderQO> {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OneBuyService oneBuyService;
	@Autowired
	private OrderBuyerInfoService buyerInfoService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private AddressesService addressesService;
	@Autowired
	private ProductSnapshotService productSnapshotService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private ShoppingCarService shoppingCarService;
	
	@Autowired
	private SKUProductService sKUProductService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AddressesService userAddresseService;
	
	@Autowired
	private OrderBuyerInfoService orderBuyerInfoService;
	
	
	@Autowired
	private OrderSKUItemService orderSKUItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private LuckNumService luckNumService;
	
	@Autowired
	private OrderConsolidationService orderConsolidationService;
	
	@Autowired
	private ShoppingCarItemService shoppingCarItemService;
	
	/**
	 * 支付异步通知
	 */

	public RESULT payOk(Map<String, String> params) {
		// TODO Auto-generated method stub
		NotifyData data = new NotifyData();
		data.create(params);
		Order order = null;
		if (StringUtils.isNotBlank(params.get("out_trade_no"))) {
			OrderQO orderQO = new OrderQO();
			orderQO.setId(data.getOut_trade_no());
			orderQO.setFetchOrderSkuItems(true);
			order = queryUnique(orderQO);
			if (order.getStatus().getPaid()) {
				return RESULT.SUCCESS;
			}
			order.paySuccess();
		}
		try {
			save(order);
			notifyService.save(data);
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		if (order == null) {
			return RESULT.ERROR;
		}else {
			//根据订单号判断是不是一元购商品并增加参加人数
			for (OrderSKUItem item: order.getOrderSKUItems()) {
				//判断是否是一元购
				OneBuy oneBuy = oneBuyService.get(item.getSkuProductId());
				if (oneBuy != null) {
					Integer partakeNum = item.getNum();
					//此处生成幸运号	根据商品id先查出已经拥有的幸运号
					LuckNumQO luckNumQO = new LuckNumQO();
					luckNumQO.setOneBuy(oneBuy);
					Integer n = 0;
					n = luckNumService.queryCount(luckNumQO);
					Integer ln = n + 10000001;
					//	LuckNum luckNum = new LuckNum();
					for (int i = 0; i < partakeNum; i++) {
						LuckNum luckNum = new LuckNum();
						luckNum.create(order,ln+i,oneBuy);
						try {
							save(luckNum);
						} catch (Exception e) {
							// 幸运号生成失败
							return RESULT.ERROR;
						}
					}
					//先判断是否还有库存
					oneBuyService.cutRepertory(oneBuy,partakeNum);
					if (oneBuy.getCountInfo().getResidueCount() <= 0 ) {
						oneBuy.changeWillPublish();
						oneBuy.getBaseInfo().setHot(false);
						try {
							update(oneBuy);
						} catch (Exception e) {
							// TODO: handle exception
							return RESULT.ERROR;
						}
					}  
					
				}
				
			}
			
		}
		return RESULT.SUCCESS;
		
	}
	
	/**
	 * 验证提交的一元购订单是否合法
	 * 合法则将数据保存，并发起支付请求
	 * 
	 * 还没有解决的问题：
	 * 此处只有一个商品的情况，不适用与购物车
	 * 此处取商品实际价格，没有参与打折，获取优惠券
	 * 此处缺少商家
	 * 
	 * @param request
	 * @param buyCommand
	 * @param userDTO 
	 * 
	 * @return 如果验证通过 则直接返回 发起支付所需的链接地址
	 * @ url =  ${contextPath}/pay/alipay/aliPay
	 * 
	 */
	public String validate(HttpServletRequest request, CreateorderFromOneBuyCommand buyCommand, UserDTO userDTO) {
		// TODO Auto-generated method stub
		OneBuyQO buyQO = new OneBuyQO();
		buyQO.setId(buyCommand.getProductId());
		OneBuy oneBuy = oneBuyService.queryUnique(buyQO);
		//计算出页面传过来的商品总价
		Double totalPrice = Double.valueOf(oneBuy.getBaseInfo().getPrice()*buyCommand.getNum());
		Double totalPrice2 = Double.valueOf(buyCommand.getTotalPrice());
		if (!totalPrice.equals(totalPrice2)) {
			return ResultJSON.resultToJSONStr(false, "支付价格与实际价格不符合");
		}
		
		//判断该订单是否已经存在
		Order mOrder = get(buyCommand.getOrderId());
		if (mOrder != null) {
			//订单重复
			return ResultJSON.resultToJSONStr(false, "请勿重复提交订单");
		}
		Order order = new Order();
		Merchant merchant = new Merchant();
		//默认后台商家信息
		order.oneBuycreate(buyCommand, merchant);
		
		//购买者信息+收货信息
		UserAddress address = addressesService.get(buyCommand.getAddressId());
		OrderBuyerInfo buyerInfo = new OrderBuyerInfo();
		User user= userService.get(userDTO.getId());
		buyerInfo.create(user, address, order);
		
		ProductSnapshotQO productSnapshotQO = new ProductSnapshotQO();
		productSnapshotQO.setProjectionProperties(new String[]{"id"});
		productSnapshotQO.setProductId(buyCommand.getProductId());
		productSnapshotQO.setSortByCreateDate(BaseQO.ORDER_DESC);
		ProductSnapshot productSnapshot  = productSnapshotService.queryUnique(productSnapshotQO);
		ProductSnapshot productSnapshot2 = productSnapshotService.get(productSnapshot.getId());
		//sku
		OrderSKUItem item = new OrderSKUItem();
		
		/*merchant.create(command, user, merchantCategory);*/
		
		item.oneBuyCreate(order,buyCommand,productSnapshot2);
		
		try {
			save(order);
			save(buyerInfo);
			save(item);
			return ResultJSON.resultToJSONStr(true, "订单已经生成");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "订单信息保存失败");
		}
		
	}

	/**
	 * 一元购订单
	 */
	public OneBuy oneBuyOrder(HttpServletRequest request, OneBuyQO buyQO){
		buyQO.setFetchCloudBrand(true);
		return oneBuyService.queryUnique(buyQO);
	}
	
	
	
	@Transactional(rollbackFor = OrderException.class)
	public Order createOrderImmediate(CreateOrderImmediateCommand command)
			throws OrderException {

		User buyerUser = userService.get(command.getUserId());
		
		// 确定商家
		Merchant merchant = merchantService.get(command.getMerchantId());
  
		// 加载购物车项和SKU商品项
		SKUProductQO sKUProductQO=new SKUProductQO();
		sKUProductQO.setProductQO(new ProductQO());
		sKUProductQO.getProductQO().setId(command.getProductId());
		sKUProductQO.setSkuInfo(command.getSkuSpecInfo());
		SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);

		if (!StringUtils.equals(sKUProduct.getProduct().getStatus(),
				Product.STATUS_ENABLE)) {
			throw new OrderException(OrderException.PRODUCT_NOT_SALE, "商品未上架");
		}


		// 查一件SKU商品的库存，不足抛业务异常
		checkStock(sKUProduct.getSkuSpecInfo(),sKUProduct.getProduct().getId(), command.getNum());

		// 收货省市区
		UserAddress address = null;
		if (!command.isPreview()) {
			if (StringUtils.isBlank(command.getAddressId())) {
				throw new OrderException(OrderException.ORDER_INFO_MISSING,
						"缺少收货地址");
			} else {
				address = userAddresseService.get(command.getAddressId());
			}
		}


		Order order = new Order();
		order.create(command, sKUProduct,merchant);

		OrderBuyerInfo orderBuyerInfo=orderBuyerInfoService.createOrderBuyerInfo(buyerUser, address, order);
		if(null==orderBuyerInfo){
			throw new OrderException(OrderException.ORDER_FAIL,
					"生成订单失败");
		}
		// 查询商品当前快照
		ProductSnapshotQO productSnapshotQO = new ProductSnapshotQO();
		productSnapshotQO.setProjectionProperties(new String[] { "id" });
		productSnapshotQO.setProductId(sKUProduct.getProduct().getId());
		productSnapshotQO.setSortByCreateDate(BaseQO.ORDER_DESC);
		ProductSnapshot snapshot = productSnapshotService.queryUnique(productSnapshotQO);
		
		ProductQO productQO=new ProductQO();
		productQO.setFetchMerchant(true);
		productQO.setId(command.getProductId());
		Product product=productService.queryUnique(productQO);
		
					
		// 根据SKU商品创建订单项
		Set<OrderSKUItem> orderSKUItems = new HashSet<OrderSKUItem>();
		OrderSKUItem orderSKUItem = new OrderSKUItem();
		orderSKUItem.create(order, sKUProduct, product.getShowInfo().getTitleImage(),command.getNum(),snapshot.getId());
		orderSKUItems.add(orderSKUItem);

		order.setOrderSKUItems(orderSKUItems);

		if (!command.isPreview()) {
			save(order);
			orderSKUItemService.save(orderSKUItem);
		}

		return order;
	}

	
	
	
	
	
	/**
	 * 购物车生成订单
	 * @throws OrderException 
	 */
	@Transactional(rollbackFor = OrderException.class)
	public OrderConsolidation createOrderFromShoppingCar(CreateOrderFromShoppingCarCommand command,String[] merchantIds,String[] shoppingCarItemIds) throws OrderException{

		// 获取购买者
		User buyerUser = userService.get(command.getUserId());
		
		CreateOrderConsolidationCommand csCommand=new CreateOrderConsolidationCommand();
		csCommand.setCreateDate(new Date());
		OrderConsolidation sc=orderConsolidationService.createMethed(csCommand);
		
		Double totalePrice=0.00;
		
		for(int i=0;i<merchantIds.length;i++){
			
			Double perTotalePrice=0.00;
			// 确定商家
			Merchant merchant = merchantService.get(merchantIds[i]);
			
			
			// 收货省市区
			UserAddress address = null;
			if (!command.isPreview()) {
				if (StringUtils.isBlank(command.getAddressId())) {
					throw new OrderException(OrderException.ORDER_INFO_MISSING,
							"缺少收货地址");
				} else {
					address = userAddresseService.get(command.getAddressId());
				}
			}

			Order order = new Order();
			order.create(command, perTotalePrice,sc,merchant );
			
			
			OrderBuyerInfo orderBuyerInfo=orderBuyerInfoService.createOrderBuyerInfo(buyerUser, address, order);
			if(null==orderBuyerInfo){
				throw new OrderException(OrderException.ORDER_FAIL,
						"生成订单失败");
			}

			Set<OrderSKUItem> orderSKUItems = new HashSet<OrderSKUItem>();
			
			ShoppingCarQO shoppingCarQO=new ShoppingCarQO();
			shoppingCarQO.setFetchShoppingCarItem(true);
			shoppingCarQO.setBatchResult(true);
			shoppingCarQO.setMerchantId(merchantIds[i]);
			List<ShoppingCar> shoppingCars = shoppingCarService.queryList(shoppingCarQO);
			
			// 遍历锁定每一件SKU商品的库存
			for (ShoppingCar shoppingCar : shoppingCars) {
				
				for (ShoppingCarItem item : shoppingCar.getItems()) {
					
					for(int j=0;j<shoppingCarItemIds.length;j++){
						if(StringUtils.equals(item.getId(), shoppingCarItemIds[j])){
							SKUProductQO sKUProductQO=new SKUProductQO();
							sKUProductQO.setProductQO(new ProductQO());
							sKUProductQO.getProductQO().setId(item.getSkuProductId());
							sKUProductQO.setSkuInfo(item.getSkuSpecInfo());
							SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);
							
							perTotalePrice+=MoneyUtil.mul(item.getOriginalPrice(),item.getNum());
							if (!StringUtils.equals(sKUProduct.getProduct().getStatus(),Product.STATUS_ENABLE)) {
								throw new OrderException(OrderException.PRODUCT_NOT_SALE,"商品未上架");
							}
							// 查询并锁定一件SKU商品的库存，不足抛业务异常
							checkStock(item.getSkuSpecInfo(),item.getSkuProductId(),item.getNum());

							// 查询商品当前快照
							ProductSnapshotQO productSnapshotQO = new ProductSnapshotQO();
							productSnapshotQO.setProjectionProperties(new String[] { "id" });
							productSnapshotQO.setProductId(sKUProduct.getProduct().getId());
							productSnapshotQO.setSortByCreateDate(BaseQO.ORDER_DESC);
							ProductSnapshot snapshot = productSnapshotService.queryUnique(productSnapshotQO);

							
							// 根据SKU商品创建订单项
							OrderSKUItem orderSKUItem = new OrderSKUItem();
							orderSKUItem.create(order, sKUProduct, item.getTitleImageUrl(),item.getNum(),snapshot.getId());
							orderSKUItems.add(orderSKUItem);
						}
					}
				}
				
				totalePrice+=perTotalePrice;
			}

			    order.setOrderSKUItems(orderSKUItems);
			    order.getBaseInfo().setTotalPrice(perTotalePrice);
				save(order);

				for (OrderSKUItem orderSKUItem : orderSKUItems) {
					orderSKUItemService.save(orderSKUItem);
				}

				// 异步删除购物车项
				deleteShoppingCarItem(shoppingCarItemIds);
				
		}

		
		sc.setName("合并订单支付");
		sc.setTotalPrice(totalePrice);
		orderConsolidationService.save(sc);
		return sc;
	}
	
	
	
	/**
	 * 下单成功后异步删除购物车项
	 * 
	 * @param shoppingCarIds
	 */
	@Async
	private void deleteShoppingCarItem(String[] shoppingCarItemIds) {
		//移除购物车项
		for(int i=0;i<shoppingCarItemIds.length;i++){
			shoppingCarItemService.deleteById(shoppingCarItemIds[i]);
		}
	}
	
	/**
	 * 除购物车
	 */
	
	public void deleteShoppingCar(String[] merchantIds){
		
		/**
		 * 1 检查购物车的项是否还有数据  
		 * 
		 * 2.有 不删除购物车
		 * 
		 * 3. 没有就移除购物车
		 */
		for(int i=0;i<merchantIds.length;i++){
			ShoppingCarQO shoppingCarQO=new ShoppingCarQO();
			shoppingCarQO.setFetchShoppingCarItem(true);
			shoppingCarQO.setBatchResult(true);
			shoppingCarQO.setMerchantId(merchantIds[i]);
			List<ShoppingCar> shoppingCars = shoppingCarService.queryList(shoppingCarQO);
			for (ShoppingCar shoppingCar : shoppingCars) {
				if(CollectionUtils.isEmpty(shoppingCar.getItems())){
					shoppingCarService.deleteById(shoppingCar.getId());
				}
			}
		}
		
	}

	
	/**
	 * 检查库存是否充足
	 * @param shoppingCar
	 * @param user
	 * @throws OrderException
	 */
	
	private void checkStock(String skuSpecInfo,String skuProductId,Integer num) throws OrderException {
		
		// 查sku库存
		SKUProductQO sKUProductQO = new SKUProductQO();
		sKUProductQO.setProductQO(new ProductQO());
		sKUProductQO.getProductQO().setId(skuProductId);
		sKUProductQO.setSkuInfo(skuSpecInfo);
		// 库存判断要加悲观写锁，防止并发争夺库存
		sKUProductQO.setWriteLock(true);
		SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);
		
		if (null == sKUProduct) {
			// 如果平台库存也没找到，抛异常
			throw new OrderException(OrderException.STOCK_NOT_FOUND,"找不到库存");
		}

		// 判断库存是否充足，充足扣减库存
		if (sKUProduct.getNumber() >= num) {
			AddNumCommand addNumCommand=new AddNumCommand();
			addNumCommand.setNum(num * -1);
			sKUProduct.addNum(addNumCommand);
			sKUProductService.update(sKUProduct);
		} else {
			// 库存不足，抛异常
			throw new OrderException(OrderException.STOCK_NOT_ENOUGH, "SKU商品:"+ sKUProduct.getProduct().getId() +sKUProduct.getSkuSpecInfo() + "库存不足");
		}
	}
	
	
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, OrderQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Order> getEntityClass() {
		// TODO Auto-generated method stub
		return Order.class;
	}


}