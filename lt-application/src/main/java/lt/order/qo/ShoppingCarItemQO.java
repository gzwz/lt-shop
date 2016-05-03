package lt.order.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "shoppingCarItemService")
public class ShoppingCarItemQO  extends BaseQO<String>{

	@QueryCondition(name = "shoppingCar", type = QueryConditionType.LEFT_JOIN)
	private ShoppingCarQO shoppingCarQO;
	
	@QueryCondition(name = "skuProductId")
	private String skuProductId;
	
	@QueryCondition(name = "skuSpecInfo")
	private String skuSpecInfo;
	
	@QueryCondition(name = "shoppingCar", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchShoppingCar;

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public ShoppingCarQO getShoppingCarQO() {
		return shoppingCarQO;
	}

	public void setShoppingCarQO(ShoppingCarQO shoppingCarQO) {
		this.shoppingCarQO = shoppingCarQO;
	}

	public Boolean getFetchShoppingCar() {
		return fetchShoppingCar;
	}

	public void setFetchShoppingCar(Boolean fetchShoppingCar) {
		this.fetchShoppingCar = fetchShoppingCar;
	}
	
}
