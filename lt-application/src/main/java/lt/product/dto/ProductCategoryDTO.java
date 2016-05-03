package lt.product.dto;

import java.util.List;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ProductCategoryDTO extends BaseDTO{
       
	private String name;
	
	private List<ProductCategoryParentDTO> parentList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductCategoryParentDTO> getParentList() {
		return parentList;
	}

	public void setParentList(List<ProductCategoryParentDTO> parentList) {
		this.parentList = parentList;
	}
	
	
	
}
