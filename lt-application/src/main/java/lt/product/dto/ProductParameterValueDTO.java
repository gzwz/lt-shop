package lt.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import lt.base.dto.BaseDTO;
import lt.product.entity.ProductParameterValue;

@SuppressWarnings("serial")
public class ProductParameterValueDTO extends BaseDTO{
	
	private String name;
	
	private String categoryName;
	
	private String paramterId;

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
	
	public static List<ProductParameterValueDTO> domainToDTO(List<ProductParameterValue> ProductParameterValues) {

		if (CollectionUtils.isEmpty(ProductParameterValues))
			return new ArrayList<>();

		List<ProductParameterValueDTO> list = new ArrayList<>();
		ProductParameterValueDTO dto = null;
		for (ProductParameterValue productParameterValue : ProductParameterValues) {
			dto = new ProductParameterValueDTO();
			dto.setId(productParameterValue.getId());
			dto.setName(productParameterValue.getValue());
			dto.setCategoryName(productParameterValue.getParameter().getName());
			list.add(dto);
		}
		return list;
	}

	public String getParamterId() {
		return paramterId;
	}

	public void setParamterId(String paramterId) {
		this.paramterId = paramterId;
	}
	

}
