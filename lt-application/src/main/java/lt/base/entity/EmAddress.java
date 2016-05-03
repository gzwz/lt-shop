package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

/**
 * 地址
 * 
 * @author wxp
 */
@Embeddable
public class EmAddress extends StringIdBaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE_ID")
	private Province province;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")
	private Area area;
	
	@Column(name = "DETAIL", length = 512)
	private String detail;

	@Column(name = "ZIPCODE", length = 16)
	private String zipCode;
	
	/**
	 * 所属用户
	 */
	@Column(name = "USER_ID", length = 64)
	private String userId;
	
	/**
	 * 是否是用户默认地址
	 */
	@Type(type = "yes_no")
	@Column(name = "DEFAULT_ADDRESS")
	private Boolean defaultAddress;
	
	public EmAddress() {
		
	}
	
	public EmAddress(Boolean initAll) {
		province = new Province();
		city = new City();
		area = new Area();
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
