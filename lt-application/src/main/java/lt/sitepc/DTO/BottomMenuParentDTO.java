package lt.sitepc.DTO;

import java.util.List;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class BottomMenuParentDTO extends BaseDTO{
	
	private String name;
	
	private String icon;
	
	private List<BottomMenuDTO> bottomMenuDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<BottomMenuDTO> getBottomMenuDTO() {
		return bottomMenuDTO;
	}

	public void setBottomMenuDTO(List<BottomMenuDTO> bottomMenuDTO) {
		this.bottomMenuDTO = bottomMenuDTO;
	}
	
}
