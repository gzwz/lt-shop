package lt.oneBuy.dto;

import java.util.ArrayList;
import java.util.List;

import lt.base.dto.BaseDTO;
import lt.oneBuy.entity.CloudBrand;

import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
public class CloudBrandDTO extends BaseDTO{

	private String brandName;
	
	private String parentName;

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
	
	public static List<CloudBrandDTO> domainToDTO(List<CloudBrand> cloudBrands) {

		if (CollectionUtils.isEmpty(cloudBrands))
			return new ArrayList<>();

		List<CloudBrandDTO> list = new ArrayList<>();
		CloudBrandDTO dto = null;
		for (CloudBrand pb : cloudBrands) {
			dto = new CloudBrandDTO();
			dto.setId(pb.getId());
			dto.setBrandName(pb.getBrandName());
			list.add(dto);
		}

		return list;
	}
}
