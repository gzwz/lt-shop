package lt.order.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@QueryConfig(daoBeanId = "orderService")
@SuppressWarnings("serial")
public class OrderBuyerInfoQO extends BaseQO<String> {

	@QueryCondition(name = "order", type = QueryConditionType.LEFT_JOIN)
	private OrderQO orderqo;

	@QueryCondition(name = "buyerUserId")
	private String buyerUserId;

	@QueryCondition(name = "province", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProvince;

	@QueryCondition(name = "city", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCity;

	@QueryCondition(name = "area", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchArea;
	
	@QueryCondition(name = "order", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOrder;

	public Boolean getFetchProvince() {
		return fetchProvince;
	}

	public void setFetchProvince(Boolean fetchProvince) {
		this.fetchProvince = fetchProvince;
	}

	public Boolean getFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(Boolean fetchCity) {
		this.fetchCity = fetchCity;
	}

	public Boolean getFetchArea() {
		return fetchArea;
	}

	public void setFetchArea(Boolean fetchArea) {
		this.fetchArea = fetchArea;
	}

	public OrderQO getOrderqo() {
		return orderqo;
	}

	public void setOrderqo(OrderQO orderqo) {
		this.orderqo = orderqo;
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public Boolean getFetchOrder() {
		return fetchOrder;
	}

	public void setFetchOrder(Boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}
	
}
