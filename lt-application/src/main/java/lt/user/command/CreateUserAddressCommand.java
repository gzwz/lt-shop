package lt.user.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 普通用户添加收货地址
 * 
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
public class CreateUserAddressCommand extends BaseCommand {

	private String userId;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 省
	 */
	private String provinceId;

	/**
	 * 市
	 */
	private String cityId;

	/**
	 * 区县
	 */
	private String areaId;

	/**
	 * 详细地址
	 */
	private String detail;

	/**
	 * 邮编
	 */
	private String zipcode;

	/**
	 * 是否设为默认地址
	 */
	private boolean defaultAddress;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

}
