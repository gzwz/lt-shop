package lt.base.dto;

import lt.base.entity.EmAddress;

public class EmbeddDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private ProvinceDTO province;

	private CityDTO city;
	
	private AreaDTO area;
	
	private String detail;

	private String zipCode;
	
	/**
	 * 所属用户
	 */
	private String userId;
	
	/**
	 * 是否是用户默认地址
	 */
	private Boolean defaultAddress;
	
	public EmbeddDTO embeddDTOConverter(EmAddress emAddress){
		if(null == emAddress){
			return null;
		}
		EmbeddDTO eDTO = new EmbeddDTO();
		eDTO.setArea(new AreaDTO().cityDTOConverter(emAddress.getArea()));
		eDTO.setCity(new CityDTO().cityDTOConverter(emAddress.getCity()));
		eDTO.setProvince(new ProvinceDTO().provinceDTOConverter(emAddress.getProvince()));
		eDTO.setDetail(emAddress.getDetail());
		eDTO.setZipCode(emAddress.getZipCode());
		return eDTO;
	}

	public ProvinceDTO getProvince() {
		return province;
	}

	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public AreaDTO getArea() {
		return area;
	}

	public void setArea(AreaDTO area) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	
	
	
}
