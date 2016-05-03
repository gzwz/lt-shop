package lt.sitepc.DTO;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class BottomMenuDTO extends BaseDTO{

	private String childrenName;

	public String getChildrenName() {
		return childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	
	
}
