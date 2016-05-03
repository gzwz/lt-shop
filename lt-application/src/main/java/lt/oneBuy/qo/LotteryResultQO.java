package lt.oneBuy.qo;

import lt.oneBuy.entity.OneBuy;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "lotteryResultServiceService")
public class LotteryResultQO extends BaseQO<String>{
	
	@QueryCondition(name ="oneBuy")
	private OneBuy oneBuy;
	
	@QueryCondition(name = "order", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOrder;
	
	@QueryCondition(name = "buyerInfo", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchBuyerInfo;
	

	public Boolean getFetchOrder() {
		return fetchOrder;
	}

	public void setFetchOrder(Boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}

	public Boolean getFetchBuyerInfo() {
		return fetchBuyerInfo;
	}

	public void setFetchBuyerInfo(Boolean fetchBuyerInfo) {
		this.fetchBuyerInfo = fetchBuyerInfo;
	}

	public OneBuy getOneBuy() {
		return oneBuy;
	}

	public void setOneBuy(OneBuy oneBuy) {
		this.oneBuy = oneBuy;
	}

	
	
}
