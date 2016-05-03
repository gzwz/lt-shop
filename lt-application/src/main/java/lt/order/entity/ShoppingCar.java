package lt.order.entity;

import java.util.Date;
import java.util.List;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.merchant.entity.Merchant;
import lt.order.command.CreateShoppingCarCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OrderBy;

import javax.persistence.CascadeType;/**
 * 购物车项
 * 
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "shopping_car")
public class ShoppingCar extends StringIdBaseEntity{

	/**
	 * 所属用户
	 */
	@Column(name = "userId", length = 64)
	private String userId;
	

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@OrderBy(clause = "createTime desc") 
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 购物车归属商信息
	 */
	private OrderMarketingInfo marketingInfo;
	
	/**
	 * 购物车项
	 */
	@OneToMany(cascade = { CascadeType.REMOVE },fetch = FetchType.LAZY,mappedBy="shoppingCar")
	@OrderBy(clause = "createTime desc") 
	private List<ShoppingCarItem> items;
	
	public void create(CreateShoppingCarCommand command,Merchant merchant){
		setId(UUIDGenerator.getUUID());
		
		setUserId(command.getUserId());
		setCreateTime(new Date());
		setMarketingInfo(new OrderMarketingInfo());
		getMarketingInfo().setOperationMerchantId(merchant.getId());
		getMarketingInfo().setOperationMerchantName(merchant.getBaseInfo().getName());
		
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public OrderMarketingInfo getMarketingInfo() {
		return marketingInfo;
	}

	public void setMarketingInfo(OrderMarketingInfo marketingInfo) {
		this.marketingInfo = marketingInfo;
	}

	public List<ShoppingCarItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCarItem> items) {
		this.items = items;
	}

}
