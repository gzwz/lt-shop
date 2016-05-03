package lt.product.dto;

import java.util.List;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ProductCategoryParentDTO  extends BaseDTO{

    private String name;
	
	private List<ProductCategoryChildrenDTO> chidrenList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductCategoryChildrenDTO> getChidrenList() {
		return chidrenList;
	}

	public void setChidrenList(List<ProductCategoryChildrenDTO> chidrenList) {
		this.chidrenList = chidrenList;
	}

	
	
}
