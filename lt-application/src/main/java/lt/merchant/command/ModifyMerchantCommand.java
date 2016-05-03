package lt.merchant.command;

import java.util.List;

import lt.base.command.CreateImageCommand;
import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyMerchantCommand extends BaseCommand{
	
	private String merchantId;

    private String merchantCategoryId;
    
    private String merchantAuthenticateInfoId;
    
    private String merchantContactInfoId;
	
	private List<String> merchantCategoryIds;
	
	
    private String	userId;
    
    private String type;
    
	/**
	 * 店名
	 */
	private String name;
	
	/**
	 * 店铺简介(备用字段)
	 */
	private String introduction;
	
	/**
	 * 店铺详情
	 */
	private String detail;
	
	/**
	 * 首图
	 */
	private String headImage;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	
     /**
      * 座机	
      */
	private String telephone;
	
	/**
	 * 省
	 */
	private String provinceId;
	
	/**
	 * 市
	 */
	private String cityId;
	
	/**
	 * 区
	 */
	private String areaId;
	
	/**
	 * 详细地址
	 */
	private String detailAddress;
	
	/**
	 * 身份证正面
	 */
	private String front;
	
	/**
	 * 身份证反面
	 * 
	 */
	private String opposite;
	
	/**
	 * 身份证图片
	 */
	private String identifyImage;
	
	/**
	 * 营业执照图片
	 */
	private String charterImage;
	
	/**
	 * 组织机构代码证图片
	 */
	private String organizationsImage;
	
	/**
	 * 登记证书图片
	 */
	private String registrationImage;
	
	/**
	 * 纳税人资质证书(图片)
	 */
	private String aptitudesImage;
	
	/**
	 * 身份证正面验证状态
	 */
	private Boolean frontValid;
	
	/**
	 * 身份证反面验证状态
	 */
	private Boolean oppositeValid;
	
	/**
	 * 身份证验证状态
	 */
	private Boolean identifyValid;
	
	/**
	 * 营业执照验证状态
	 */
	private Boolean charterValid;
	
	/**
	 * 组织机构代码验证状态
	 */
	private Boolean organizationsValid;
	
	/**
	 * 登记证书验证状态
	 */
	private Boolean registrationValid;
	
	/**
	 * 纳税人资质验证状态
	 */
	private Boolean aptitudesValid;
	
	/**
	 * 实体id
	 */
	private String domainId;

	/**
	 * 实体名称
	 */
	private String domainName;

	/**
	 * 实体类型
	 */
	private String domainType;
	
	/**
	 * 经度
	 */
	private Double longitude;

	/**
	 * 纬度
	 */
	private Double latitude;
	
	private String mainCore;
	
	/**
	 * 创建多张图片
	 */
	private List<CreateImageCommand> createImageCommandList;
	
	private String images;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getOpposite() {
		return opposite;
	}

	public void setOpposite(String opposite) {
		this.opposite = opposite;
	}


	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public String getMerchantCategoryId() {
		return merchantCategoryId;
	}

	public void setMerchantCategoryId(String merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}

	public List<String> getMerchantCategoryIds() {
		return merchantCategoryIds;
	}

	public void setMerchantCategoryIds(List<String> merchantCategoryIds) {
		this.merchantCategoryIds = merchantCategoryIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifyImage() {
		return identifyImage;
	}

	public void setIdentifyImage(String identifyImage) {
		this.identifyImage = identifyImage;
	}

	public String getCharterImage() {
		return charterImage;
	}

	public void setCharterImage(String charterImage) {
		this.charterImage = charterImage;
	}

	public String getOrganizationsImage() {
		return organizationsImage;
	}

	public void setOrganizationsImage(String organizationsImage) {
		this.organizationsImage = organizationsImage;
	}

	public String getRegistrationImage() {
		return registrationImage;
	}

	public void setRegistrationImage(String registrationImage) {
		this.registrationImage = registrationImage;
	}

	public String getAptitudesImage() {
		return aptitudesImage;
	}

	public void setAptitudesImage(String aptitudesImage) {
		this.aptitudesImage = aptitudesImage;
	}

	public Boolean getFrontValid() {
		return frontValid;
	}

	public void setFrontValid(Boolean frontValid) {
		this.frontValid = frontValid;
	}

	public Boolean getOppositeValid() {
		return oppositeValid;
	}

	public void setOppositeValid(Boolean oppositeValid) {
		this.oppositeValid = oppositeValid;
	}

	public Boolean getIdentifyValid() {
		return identifyValid;
	}

	public void setIdentifyValid(Boolean identifyValid) {
		this.identifyValid = identifyValid;
	}

	public Boolean getCharterValid() {
		return charterValid;
	}

	public void setCharterValid(Boolean charterValid) {
		this.charterValid = charterValid;
	}

	public Boolean getOrganizationsValid() {
		return organizationsValid;
	}

	public void setOrganizationsValid(Boolean organizationsValid) {
		this.organizationsValid = organizationsValid;
	}

	public Boolean getRegistrationValid() {
		return registrationValid;
	}

	public void setRegistrationValid(Boolean registrationValid) {
		this.registrationValid = registrationValid;
	}

	public Boolean getAptitudesValid() {
		return aptitudesValid;
	}

	public void setAptitudesValid(Boolean aptitudesValid) {
		this.aptitudesValid = aptitudesValid;
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

	public String getMainCore() {
		return mainCore;
	}

	public void setMainCore(String mainCore) {
		this.mainCore = mainCore;
	}

	public List<CreateImageCommand> getCreateImageCommandList() {
		return createImageCommandList;
	}

	public void setCreateImageCommandList(
			List<CreateImageCommand> createImageCommandList) {
		this.createImageCommandList = createImageCommandList;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getMerchantAuthenticateInfoId() {
		return merchantAuthenticateInfoId;
	}

	public void setMerchantAuthenticateInfoId(String merchantAuthenticateInfoId) {
		this.merchantAuthenticateInfoId = merchantAuthenticateInfoId;
	}

	public String getMerchantContactInfoId() {
		return merchantContactInfoId;
	}

	public void setMerchantContactInfoId(String merchantContactInfoId) {
		this.merchantContactInfoId = merchantContactInfoId;
	}
	
	
}
