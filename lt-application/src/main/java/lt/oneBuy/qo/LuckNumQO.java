package lt.oneBuy.qo;

import lt.oneBuy.entity.OneBuy;
import lt.order.entity.Order;
import lt.order.qo.OrderQO;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
public class LuckNumQO extends BaseQO<String>{
	
	@QueryCondition(name = "oneBuy",type = QueryConditionType.LEFT_JOIN)
	private OneBuyQO oneBuyQO;
	
	@QueryCondition(name = "order",type = QueryConditionType.LEFT_JOIN)
	private OrderQO orderQO;
	
	@QueryCondition(name = "oneBuy")
	private OneBuy oneBuy;
	
	@QueryCondition(name = "order")
	private Order order;
	
	@QueryCondition(name = "luckNumber")
	private Integer luckNumber;
	
	@QueryCondition(name = "order",type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOrder;

    @QueryCondition(name = "oneBuy",type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOneBuy;

	public Integer getLuckNumber() {
		return luckNumber;
	}

	public void setLuckNumber(Integer luckNumber) {
		this.luckNumber = luckNumber;
	}


	public OneBuyQO getOneBuyQO() {
		return oneBuyQO;
	}

	public void setOneBuyQO(OneBuyQO oneBuyQO) {
		this.oneBuyQO = oneBuyQO;
	}

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

	public Boolean getFetchOneBuy() {
		return fetchOneBuy;
	}

	public void setFetchOneBuy(Boolean fetchOneBuy) {
		this.fetchOneBuy = fetchOneBuy;
	}

	public OneBuy getOneBuy() {
		return oneBuy;
	}

	public void setOneBuy(OneBuy oneBuy) {
		this.oneBuy = oneBuy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
