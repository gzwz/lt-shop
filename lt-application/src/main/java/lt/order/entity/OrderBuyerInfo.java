package lt.order.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.user.entity.User;
import lt.user.entity.UserAddress;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "order_buy_info")
public class OrderBuyerInfo  extends StringIdBaseEntity{

	
	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=Order.class)
	@JoinColumn(name = "order_id")
	private Order order;
	
	/**
	 * 购买者用户id
	 */
	@Column(name = "buyer_user_id")
	private String buyerUserId;

	/**
	 * 购买者帐号名
	 */
	@Column(name = "buyer_name")
	private String buyerName;

	/**
	 * 购买者手机号
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 收货省
	 */
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "province_id")
	private Province province;

	/**
	 * 收货市
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	/**
	 * 收货区县
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	/**
	 * 收货详细地址
	 */
	@Column(name = "detail_address", length = 256)
	private String detail;

	/**
	 * 收货邮编
	 */
	@Column(name = "zip_code", length = 16)
	private String zipCode;

	/**
	 * 收件人姓名
	 */
	@Column(name = "consignee_name", length = 32)
	private String consigneeName;

	/**
	 * 收件人电话
	 */
	@Column(name = "consignee_phone", length = 32)
	private String consigneePhone;

	
    public void create(User buyerUser,UserAddress address,Order order){
		
		setId(UUIDGenerator.getUUID());
		
		setOrder(order);
		if(null!=address){
			setProvince(address.getProvince());
			setCity(address.getCity());
			setArea(address.getArea());
			setDetail(address.getDetail());
			setZipCode(address.getZipCode());
			setConsigneeName(address.getName());
			setConsigneePhone(address.getMobile());
			
		}
		
		setBuyerName(buyerUser.getBaseInfo().getName());
		setMobile(buyerUser.getContactInfo().getMobile());
		setBuyerUserId(buyerUser.getId());
		
	}

	
	
	
	
	
	
	
	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}



 
}
