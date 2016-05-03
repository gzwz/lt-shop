package lt.order.service;

import lt.merchant.entity.Merchant;
import lt.merchant.service.MerchantService;
import lt.order.command.CreateShoppingCarCommand;
import lt.order.command.CreateShoppingCarItemCommand;
import lt.order.entity.ShoppingCar;
import lt.order.entity.ShoppingCarItem;
import lt.order.qo.ShoppingCarItemQO;
import lt.order.qo.ShoppingCarQO;
import lt.product.entity.Product;
import lt.product.entity.SKUProduct;
import lt.product.qo.ProductQO;
import lt.product.qo.SKUProductQO;
import lt.product.service.ProductService;
import lt.product.service.SKUProductService;
import lt.user.qo.UserQO;
import lt.user.service.UserService;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.ResultJSON;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingCarService extends BaseDao<ShoppingCar, ShoppingCarQO> {

	@Autowired
	private UserService userService;
	
	/**
	 * service注解
	 */
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SKUProductService sKUProductService;
	
	@Autowired
	private ShoppingCarItemService shoppingCarItemService;
	
	@Autowired
	private MerchantService merchantService;
	
	public String createShoppingCar(CreateShoppingCarCommand command) {

		if(StringUtils.isNotBlank(command.getUserId())&&StringUtils.isNotBlank(command.getProductId())){
			UserQO userQO=new UserQO();
			userQO.setId(command.getUserId());
			Integer count=userService.queryCount(userQO);
			
			ProductQO productQO=new ProductQO();
			productQO.setId(command.getProductId());
			Product product=productService.queryUnique(productQO);
			 
			if(count==1&&null!=product){
				try {
					
					/**
					 * 1.检查购物车是否存在
					 * 
					 * 2.如果不存在 就添加购物车
					 * 
					 * 3.如果存在就就修改购物车项
					 */
					
					ShoppingCarQO shoppingCarQO=new ShoppingCarQO();
					shoppingCarQO.setUserId(command.getUserId());
					shoppingCarQO.setOperationMerchantId(command.getOperationMerchantId());
					int countShopPingCar=queryCount(shoppingCarQO);
					ShoppingCar shoppingCar=queryUnique(shoppingCarQO);
					
					
					SKUProductQO sKUProductQO=new SKUProductQO();
				    sKUProductQO.setSkuSpecInfo(command.getSkuSpecInfo());
					sKUProductQO.setProductQO(new ProductQO());
					sKUProductQO.getProductQO().setId(product.getId());
					SKUProduct sKUProduct=sKUProductService.queryUnique(sKUProductQO);
					
					
					if(countShopPingCar>0){
						
						
						
						/**
						 * 1.检查购物车项里是否存在
						 * 
						 * 2.存在就相加
						 * 
						 * 3.不存在就创建
						 */
						
						ShoppingCarItemQO ItemCarQO=new ShoppingCarItemQO();
						ItemCarQO.setSkuProductId(command.getProductId());
						ItemCarQO.setSkuSpecInfo(command.getSkuSpecInfo());
						int countItem=shoppingCarItemService.queryCount(ItemCarQO);
						
						ShoppingCarItem shoppingCarItem=shoppingCarItemService.queryUnique(ItemCarQO);
						
						if(countItem>0){
							/*if(shoppingCarItem.getNum()>sKUProduct.getNumber()){
								return ResultJSON.resultToJSONStr(false, "库存不足");
							}else{*/
							shoppingCarItem.setNum(shoppingCarItem.getNum()+command.getNum());
							shoppingCarItemService.update(shoppingCarItem);
							/*}*/
						}else{
							//创建购物车项
							createItem(sKUProduct, shoppingCar, command);
						}
						
					}else{
						
						//商家
						Merchant merchant=merchantService.get(command.getOperationMerchantId());
						
						ShoppingCar shopCar = new ShoppingCar();
						shopCar.create(command, merchant);
						save(shopCar);
						//创建购物车项
						createItem(sKUProduct, shopCar, command);
						
						update(shopCar);
					}
					
					
				} catch (Exception e) {
					return ResultJSON.resultToJSONStr(false, "加入购物失败");
				}
				return ResultJSON.resultToJSONStr(true, "加入购物车成功");
			}
		}
		return ResultJSON.resultToJSONStr(false, "加入购物失败");
	}
	
	//创建购物车项
	public ShoppingCarItem createItem(SKUProduct sKUProduct,ShoppingCar shoppingCar,CreateShoppingCarCommand command){
		
		CreateShoppingCarItemCommand itemCommand=new CreateShoppingCarItemCommand();
		itemCommand.setSkuProductId(sKUProduct.getProduct().getId());
		itemCommand.setOriginalPrice(sKUProduct.getPrice());
		itemCommand.setProductName(sKUProduct.getProduct().getShowInfo().getName());
		itemCommand.setTitleImageUrl(sKUProduct.getProduct().getShowInfo().getTitleImage());
		itemCommand.setMerchantName(sKUProduct.getProduct().getMerchant().getBaseInfo().getName());
		itemCommand.setMerChantId(sKUProduct.getProduct().getMerchant().getId());
		itemCommand.setNum(command.getNum());
		itemCommand.setSkuSpecInfo(command.getSkuSpecInfo());
		itemCommand.setProductCatoryName(command.getProductCatoryName());
		
		ShoppingCarItem shoppingCarItem=shoppingCarItemService.createShoppingCarItem(itemCommand, shoppingCar);
		return shoppingCarItem;
	}

	/*public ShoppingCar modifyShoppingCar(ModifyShoppingCarCommand command) {

		ShoppingCar shoppingCar = get(command.getShoppingCarId());
		shoppingCar.modify(command);
		update(shoppingCar);
		return shoppingCar;
	}
	
	
	public ShoppingCar modifyNum(ModifyShoppingCarNumCommand command){
		
		ShoppingCar shoppingCar = get(command.getShoppingCarId());
		shoppingCar.updateNum(command);
		update(shoppingCar);
		return shoppingCar;
	}
	*/
	
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, ShoppingCarQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ShoppingCar> getEntityClass() {
		// TODO Auto-generated method stub
		return ShoppingCar.class;
	}

}
