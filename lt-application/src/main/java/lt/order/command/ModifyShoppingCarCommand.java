package lt.order.command;

import lt.order.entity.OrderMarketingInfo;
import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyShoppingCarCommand extends BaseCommand{

	private String ShoppingCarId;
	/**
	 * 所属用户
	 */
	private String userId;
	

	/**
	 * 购物车归属商信息
	 */
	private OrderMarketingInfo marketingInfo;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public OrderMarketingInfo getMarketingInfo() {
		return marketingInfo;
	}


	public void setMarketingInfo(OrderMarketingInfo marketingInfo) {
		this.marketingInfo = marketingInfo;
	}


	public String getShoppingCarId() {
		return ShoppingCarId;
	}


	public void setShoppingCarId(String shoppingCarId) {
		ShoppingCarId = shoppingCarId;
	}
	
	
	
}
