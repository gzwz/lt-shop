package lt.base.dto;

import lt.base.entity.Province;

public class ProvinceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String code;
	
	public ProvinceDTO() {
	}
	
	public ProvinceDTO(String code, String name) {
		this.name = name;
		this.code = code;
	}
	
	public ProvinceDTO provinceDTOConverter(Province province){
		if(null == province){
			return null;
		}
		ProvinceDTO pDTO = new ProvinceDTO();
		pDTO.setCode(province.getCode());
		pDTO.setId(province.getId());
		pDTO.setName(province.getName());
		return pDTO;
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

}
