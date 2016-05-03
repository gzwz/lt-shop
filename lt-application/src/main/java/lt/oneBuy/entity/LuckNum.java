package lt.oneBuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.order.entity.Order;

import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "LUCKNUM")
public class LuckNum extends StringIdBaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId" ,nullable = false)
	private Order order ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oneBuyId" ,nullable = false)
	private OneBuy oneBuy;
	
	@Column (length = 30)
	private Integer luckNumber;
	
	public void create(Order order2,Integer num, OneBuy oneBuy2) {
		// TODO Auto-generated method stub
		setId(UUIDGenerator.getUUID());
		setLuckNumber(num);
		setOneBuy(oneBuy2);
		setOrder(order2);
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getLuckNumber() {
		return luckNumber;
	}

	public void setLuckNumber(Integer luckNumber) {
		this.luckNumber = luckNumber;
	}

	public OneBuy getOneBuy() {
		return oneBuy;
	}

	public void setOneBuy(OneBuy oneBuy) {
		this.oneBuy = oneBuy;
	}

	
	
}
