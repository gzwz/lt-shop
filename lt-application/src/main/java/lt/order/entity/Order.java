package lt.order.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.MoneyUtil;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.merchant.entity.Merchant;
import lt.order.command.CreateOrderFromShoppingCarCommand;
import lt.order.command.CreateOrderImmediateCommand;
import lt.order.command.CreateorderFromOneBuyCommand;
import lt.product.entity.SKUProduct;
import lt.utils.OrderUtil;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 订单 id为订单号： 当前日期精确到秒数 + 3位随机数 共 16 or 17 左右 位
 * DateUtil.getDate()+RandomUtil.numberString(3)
 * 
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ORDER")
public class Order extends StringIdBaseEntity {

	@JoinColumn(name = "sc_id")
	private String scId;
	
	/**
	 * 订单基本信息
	 */
	private OrderBaseInfo baseInfo;
	
	

	private OrderStatus status;
	
	/**
	 * 订单归属信息
	 */
	private OrderMarketingInfo marketingInfo;
	
	/**
	 * 支付备注
	 */
	@Column(name = "remark", length = 512)
	private String remark;
	
	@Column(name = "pick_up")
	private  String   pickUp;
	
	public final static String PK_SINCE = "since"; // 自提
	public final static String PK_LOGISTICAL = "logistical"; // 物流
	
/*	@OneToOne(fetch=FetchType.LAZY,mappedBy = "order")
	private OrderBuyerInfo buyerInfo;*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderSKUItem> orderSKUItems;

	/**
	 * 创建一元购商品的订单详情
	 * @param merchant 
	 */
	public void oneBuycreate(CreateorderFromOneBuyCommand buyCommand, Merchant merchant) {
		// TODO Auto-generated method stub
		setId(buyCommand.getOrderId());
		setBaseInfo(new OrderBaseInfo());
		getBaseInfo().setTotalPrice(Double.valueOf(buyCommand.getTotalPrice()));
		getBaseInfo().setCashPayPrice(Double.valueOf(buyCommand.getTotalPrice()));
//		getBaseInfo().setPaymentOutId();
//		getBaseInfo().setCashPayChannel(buyCommand.getPayment());
		getBaseInfo().setCreateDate(new Date());
//		getBaseInfo().setPayDate(payDate);
		
		setMarketingInfo(new OrderMarketingInfo());
		getMarketingInfo().setOperationMerchantId("123456");
		getMarketingInfo().setOperationMerchantName("e-kcw.com");
		
		setStatus(new OrderStatus());
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_CREATE);
		getStatus().setPaid(false);
		getStatus().setCanPay(true);
		getStatus().setClose(false);
		
	}

	/**
	 * 单商品创建
	 * 
	 * @param user
	 * @param skuProduct
	 * @param num
	 * @param operationMerchant
	 */
	public void create(CreateOrderImmediateCommand command,SKUProduct skuProduct,Merchant merchant) {
		setId(OrderUtil.getOrderId());

		setBaseInfo(new OrderBaseInfo());
		getBaseInfo().setCreateDate(new Date());
		getBaseInfo().setCashPayChannel(command.getCashPayChannel());
		getBaseInfo().setTotalPrice(0d);
		getBaseInfo().setTotalPrice(
				getBaseInfo().getTotalPrice()
						+ MoneyUtil.mul(skuProduct.getPrice(), command.getNum()));

		setStatus(new OrderStatus());
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_CREATE);
		getStatus().setPaid(false);
		getStatus().setCanPay(true);
		getStatus().setClose(false);
		
		setRemark(command.getRemark());
		setPickUp(command.getPickUp());
		
		
		setMarketingInfo(new OrderMarketingInfo());
		getMarketingInfo().setOperationMerchantId(merchant.getId());
		getMarketingInfo().setOperationMerchantName(merchant.getBaseInfo().getName());
	}

	/**
	 * 购物车来的多商品创建
	 * 
	 * @param user
	 * @param skuProduct
	 * @param num
	 * @param operationMerchant
	 */

	public void create(CreateOrderFromShoppingCarCommand command,Double perTotalePrice,OrderConsolidation sc,Merchant merchant) {
		setId(OrderUtil.getOrderId());
		
		setBaseInfo(new OrderBaseInfo());
		getBaseInfo().setCreateDate(new Date());
		getBaseInfo().setCashPayChannel(command.getCashPayChannel());
		getBaseInfo().setTotalPrice(0d);
		getBaseInfo().setTotalPrice(perTotalePrice);

		setStatus(new OrderStatus());
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_CREATE);
		getStatus().setPaid(false);
		getStatus().setCanPay(true);
		getStatus().setClose(false);
		
		setRemark(command.getRemark());
		setPickUp(command.getPickUp());
		
		setScId(sc.getId());
		
		setMarketingInfo(new OrderMarketingInfo());
		getMarketingInfo().setOperationMerchantId(merchant.getId());
		getMarketingInfo().setOperationMerchantName(merchant.getBaseInfo().getName());
	}

	/**
	 * 支付成功
	 */
	public void paySuccess() {

		getBaseInfo().setPayDate(new Date());

		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_PAID);
		getStatus().setPaid(true);
		getStatus().setCanPay(false);
	}

	/**
	 * 取消订单
	 */
	public void cancel() {
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_CANCEL);
		getStatus().setCanPay(false);
	}

	public void remove() {
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_REMOVE);
	}
	
	//待评价
	public void evaluate() {
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_UNEVALUATE);
	}

	public void finish() {
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_FINISH);
	}

	public void close() {
		getStatus().setClose(true);
	}

	public OrderBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(OrderBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Set<OrderSKUItem> getOrderSKUItems() {
		return orderSKUItems;
	}

	public void setOrderSKUItems(Set<OrderSKUItem> orderSKUItems) {
		this.orderSKUItems = orderSKUItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public OrderMarketingInfo getMarketingInfo() {
		return marketingInfo;
	}

	public void setMarketingInfo(OrderMarketingInfo marketingInfo) {
		this.marketingInfo = marketingInfo;
	}

	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}


}
