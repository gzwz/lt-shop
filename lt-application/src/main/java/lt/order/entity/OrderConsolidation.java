package lt.order.entity;

import java.util.Date;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.order.command.CreateOrderConsolidationCommand;
import lt.utils.OrderUtil;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "order_consolidation")
public class OrderConsolidation extends StringIdBaseEntity{
	
	/**
	 * 合并订单总价
	 */
	@Column(name = "total_price", columnDefinition = M.MONEY_COLUMN)
	private Double totalPrice;
	
	/**
	 * 创建时间
	 * 
	 */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * 支付时间
	 */
	@Column(name = "pay_date")
	private Date payDate;
	
	/**
	 * 合并后的订单昵称
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 合并后的订单状态
	 */
	private OrderStatus status;
	
	
	public void create(CreateOrderConsolidationCommand command){
		
		setId(OrderUtil.getConsolidation());
		
		setCreateDate(new Date());
		
		setName(command.getName());
		setTotalPrice(command.getTotalPrice());
		
		setStatus(new OrderStatus());
		getStatus().setCanPay(true);
		getStatus().setClose(false);
		getStatus().setCurrentValue(OrderStatus.ORDER_STATUS_CREATE);
		getStatus().setPaid(false);
		
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
