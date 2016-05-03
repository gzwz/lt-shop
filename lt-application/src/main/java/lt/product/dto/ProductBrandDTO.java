package lt.product.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lt.base.dto.BaseDTO;
import lt.product.entity.ProductBrand;

@SuppressWarnings("serial")
public class ProductBrandDTO extends BaseDTO{

	private String brandName;
	
	private String parentName;
	
	private String signImage;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public static List<ProductBrandDTO> domainToDTO(List<ProductBrand> productBrands) {

		if (CollectionUtils.isEmpty(productBrands))
			return new ArrayList<>();

		List<ProductBrandDTO> list = new ArrayList<>();
		ProductBrandDTO dto = null;
		for (ProductBrand pb : productBrands) {
			dto = new ProductBrandDTO();
			dto.setId(pb.getId());
			dto.setBrandName(pb.getBrandName());
			list.add(dto);
		}

		return list;
	}
	
}
