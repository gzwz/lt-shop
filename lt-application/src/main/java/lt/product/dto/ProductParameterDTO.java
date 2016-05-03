package lt.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import lt.base.dto.BaseDTO;
import lt.product.entity.ProductParameter;

@SuppressWarnings("serial")
public class ProductParameterDTO extends BaseDTO{
	
	private String name;
	
	private String categoryName;
	
	private List<ProductParameterValueDTO> productParameterValueDTO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	

	public static List<ProductParameterDTO> domainToDTO(List<ProductParameter> productParameters) {

		if (CollectionUtils.isEmpty(productParameters))
			return new ArrayList<>();

		List<ProductParameterDTO> list = new ArrayList<>();
		ProductParameterDTO dto = null;
		for (ProductParameter productParameter : productParameters) {
			dto = new ProductParameterDTO();
			dto.setId(productParameter.getId());
			dto.setName(productParameter.getName());
			list.add(dto);
		}

		return list;
	}

	public List<ProductParameterValueDTO> getProductParameterValueDTO() {
		return productParameterValueDTO;
	}

	public void setProductParameterValueDTO(
			List<ProductParameterValueDTO> productParameterValueDTO) {
		this.productParameterValueDTO = productParameterValueDTO;
	}

	
	
}
