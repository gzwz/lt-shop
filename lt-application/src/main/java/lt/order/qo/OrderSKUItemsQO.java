package lt.order.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
public class OrderSKUItemsQO extends BaseQO<String>{
	
	@QueryCondition(name = "skuProductId")
	private String skuProductId;

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}
	
	
}
