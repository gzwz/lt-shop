package lt.product.dto;

import java.util.List;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ScreeningConditionDTO extends BaseDTO{

    private String name;
	
	private List<ScreeningConditionParentDTO> parentList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ScreeningConditionParentDTO> getParentList() {
		return parentList;
	}

	public void setParentList(List<ScreeningConditionParentDTO> parentList) {
		this.parentList = parentList;
	}
	
}
