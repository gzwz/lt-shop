package lt.merchant.entity;

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
import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
/**
 * 商家联系信息
 * @author wangxiaoping
 *
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "merchant_Contact_Info")
public class MerchantContactInfo extends StringIdBaseEntity{
   
	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=Merchant.class)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
	/**
	 * 手机
	 */
	@Column(name = "mobile")
	private String mobile;
	
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	
     /**
      * 座机	
      */
	@Column(name = "telephone")
	private String telephone;
	
	/**
	 * 经度
	 */
	private Double longitude;

	/**
	 * 纬度
	 */
	private Double latitude;
	
	/**
	 * 店铺详情
	 */
	@Column(name = "detail", columnDefinition = M.TEXT_COLUMN)
	private String detail;
	
	/**
	 * 地址
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;
	
	@Column(name = "detail_address", length = 512)
	private String detailAddress;
	
	public void create(CreateMerchantCommand command,Merchant merchant,Province province,City city,Area area){
		setId(UUIDGenerator.getUUID());
		
		setArea(area);
		setCity(city);
		setProvince(province);
		setDetail(command.getDetail());
		setDetailAddress(command.getDetailAddress());
		setEmail(command.getEmail());
		setMerchant(merchant);
		setMobile(command.getMobile());
		setTelephone(command.getTelephone());
		setLatitude(command.getLatitude());
		setLongitude(command.getLongitude());
	}
	
	
	public void modify(ModifyMerchantCommand command,Merchant merchant,Province province,City city,Area area){
		
		setArea(area);
		setCity(city);
		setProvince(province);
		setDetail(command.getDetail());
		setDetailAddress(command.getDetailAddress());
		setEmail(command.getEmail());
		setMerchant(merchant);
		setMobile(command.getMobile());
		setTelephone(command.getTelephone());
		setLatitude(command.getLatitude());
		setLongitude(command.getLongitude());
	}
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	

}
