package lt.order.qo;

import java.util.Date;

import lt.order.entity.OrderMarketingInfo;
import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "orderService")
public class OrderQO extends BaseQO<String>{
	
	@QueryCondition(name = "orderSKUItems", type = QueryConditionType.LEFT_JOIN)
	private OrderSKUItemsQO orderSKUItemsQO;
	
	@QueryCondition(name = "orderSKUItems", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchOrderSkuItems;
	
	@QueryCondition(name = "buyerInfo", type = QueryConditionType.LEFT_JOIN)
	private OrderBuyerInfoQO orderBuyerInfoQO;
	
	@QueryCondition(name = "buyerInfo", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchBuyer;
	
	
	private String orderId;
	
	@QueryCondition(name = "marketingInfo")
	private OrderMarketingInfo marketingInfo;
	
	@QueryCondition(name = "scId")
	private String scId;
	
	/**
	 * 交易状态
	 */
	@QueryCondition(name = "status.currentValue", type = QueryConditionType.EQ)
	private String currentValue;
	
 
	/**
	 * 最早创建时间（含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.GE)
	private Date geCreateDate;

	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.ORDER)
	private Integer orderBy;

	/**
	 * 最晚创建时间（含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.LE)
	private Date leCreateDate;

	/**
	 * 最早创建时间（不含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.GT)
	private Date gtCreateDate;

	/**
	 * 最晚创建时间（不含）
	 */
	@QueryCondition(name = "baseInfo.createDate", type = QueryConditionType.LT)
	private Date ltCreateDate;
	
 
	
	public Boolean getFetchOrderSkuItems() {
		return fetchOrderSkuItems;
	}

	public void setFetchOrderSkuItems(Boolean fetchOrderSkuItems) {
		this.fetchOrderSkuItems = fetchOrderSkuItems;
	}

	public OrderSKUItemsQO getOrderSKUItemsQO() {
		return orderSKUItemsQO;
	}

	public void setOrderSKUItemsQO(OrderSKUItemsQO orderSKUItemsQO) {
		this.orderSKUItemsQO = orderSKUItemsQO;
	}

	public Date getGeCreateDate() {
		return geCreateDate;
	}

	public void setGeCreateDate(Date geCreateDate) {
		this.geCreateDate = geCreateDate;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Date getLeCreateDate() {
		return leCreateDate;
	}

	public void setLeCreateDate(Date leCreateDate) {
		this.leCreateDate = leCreateDate;
	}

	public Date getGtCreateDate() {
		return gtCreateDate;
	}

	public void setGtCreateDate(Date gtCreateDate) {
		this.gtCreateDate = gtCreateDate;
	}

	public Date getLtCreateDate() {
		return ltCreateDate;
	}

	public void setLtCreateDate(Date ltCreateDate) {
		this.ltCreateDate = ltCreateDate;
	}
	

	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	public Boolean getFetchBuyer() {
		return fetchBuyer;
	}

	public void setFetchBuyer(Boolean fetchBuyer) {
		this.fetchBuyer = fetchBuyer;
	}

	public OrderBuyerInfoQO getOrderBuyerInfoQO() {
		return orderBuyerInfoQO;
	}

	public void setOrderBuyerInfoQO(OrderBuyerInfoQO orderBuyerInfoQO) {
		this.orderBuyerInfoQO = orderBuyerInfoQO;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public OrderMarketingInfo getMarketingInfo() {
		return marketingInfo;
	}

	public void setMarketingInfo(OrderMarketingInfo marketingInfo) {
		this.marketingInfo = marketingInfo;
	}
	
	
}
