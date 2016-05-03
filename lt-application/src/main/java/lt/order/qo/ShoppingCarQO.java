package lt.order.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "shoppingCarService")
public class ShoppingCarQO extends BaseQO<String>{

	@QueryCondition(name = "userId")
	private String userId;
	
	@QueryCondition(name = "marketingInfo.operationMerchantId")
	private String merchantId;
	
	@QueryCondition(name = "marketingInfo.operationMerchantId")
	private String operationMerchantId;
	
	@QueryCondition(name = "items", type = QueryConditionType.LEFT_JOIN)
	private ShoppingCarItemQO shoppingCarItemQO;
	
	
	@QueryCondition(name = "items", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchShoppingCarItem;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperationMerchantId() {
		return operationMerchantId;
	}

	public void setOperationMerchantId(String operationMerchantId) {
		this.operationMerchantId = operationMerchantId;
	}

	public ShoppingCarItemQO getShoppingCarItemQO() {
		return shoppingCarItemQO;
	}

	public void setShoppingCarItemQO(ShoppingCarItemQO shoppingCarItemQO) {
		this.shoppingCarItemQO = shoppingCarItemQO;
	}

	public Boolean getFetchShoppingCarItem() {
		return fetchShoppingCarItem;
	}

	public void setFetchShoppingCarItem(Boolean fetchShoppingCarItem) {
		this.fetchShoppingCarItem = fetchShoppingCarItem;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
}
