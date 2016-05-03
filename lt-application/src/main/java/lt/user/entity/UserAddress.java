package lt.user.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.user.command.CreateUserAddressCommand;
import lt.user.command.ModifyUserAddressCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


/**
 * 地址
 * 
 * @author 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "USER_ADDRESS")
public class UserAddress extends StringIdBaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 收件人姓名
	 */
	@Column(name = "name", length = 32)
	private String name;

	/**
	 * 收件人手机
	 */
	@Column(name = "mobile", length = 16)
	private String mobile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	@Column(name = "detail", length = 512)
	private String detail;

	@Column(name = "zipCode", length = 16)
	private String zipCode;

	/**
	 * 是否是用户默认地址
	 */
	@Type(type = "yes_no")
	@Column(name = "DEFAULT_ADDRESS")
	private Boolean defaultAddress;

	public UserAddress() {

	}

	public void create(CreateUserAddressCommand command, User user,
			Province province, City city, Area area) {
		setId(UUIDGenerator.getUUID());
		setDefaultAddress(true);
		setUser(user);
		setProvince(province);
		setCity(city);
		setArea(area);
		
		setZipCode(command.getZipcode());
        setMobile(command.getMobile());
        setName(command.getName());
        
		setDefaultAddress(command.isDefaultAddress());
		setDetail(command.getDetail());
	}

	public void modify(ModifyUserAddressCommand command, Province province,
			City city, Area area) {
		setProvince(province);
		setCity(city);
		setArea(area);
		
		setZipCode(command.getZipcode());
        setMobile(command.getMobile());
        setName(command.getName());
		setDetail(command.getDetail());
	}

	public void settingDefault() {
		setDefaultAddress(true);
	}
	
	public void cancelDefault() {
		setDefaultAddress(false);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
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

}
