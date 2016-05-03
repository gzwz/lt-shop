package lt.base.dto;

import lt.base.entity.Area;

public class AreaDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	private CityDTO city;
	
	public AreaDTO cityDTOConverter(Area area){
		if(null == area){
			return null;
		}
		
		AreaDTO aDTO =  new AreaDTO();
		aDTO.setCode(area.getCode());
		aDTO.setName(area.getName());
		aDTO.setId(area.getId());
		
		return aDTO;
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

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

}
