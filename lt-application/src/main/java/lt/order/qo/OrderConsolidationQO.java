package lt.order.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "orderConsolidationService")
@SuppressWarnings("serial")
public class OrderConsolidationQO extends BaseQO<String>{

	
	@QueryCondition(name = "orders", type = QueryConditionType.LEFT_JOIN)
	private OrderQO orderQO;
	
	@QueryCondition(name = "orders", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOrder;

	public OrderQO getOrderQO() {
		return orderQO;
	}

	public void setOrderQO(OrderQO orderQO) {
		this.orderQO = orderQO;
	}

	public Boolean getFetchOrder() {
		return fetchOrder;
	}

	public void setFetchOrder(Boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}
	
}
