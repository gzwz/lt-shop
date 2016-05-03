package lt.base.dto;

import lt.base.entity.City;

public class CityDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	private ProvinceDTO province;
	
	public CityDTO cityDTOConverter(City city){
		if(null == city){
				return null;
		}
		CityDTO cDTO = new CityDTO();
		cDTO.setId(city.getId());
		cDTO.setCode(city.getCode());
		cDTO.setName(city.getName());
		return cDTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ProvinceDTO getProvince() {
		return province;
	}

	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}

}
