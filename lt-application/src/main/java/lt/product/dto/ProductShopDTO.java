package lt.product.dto;

import java.util.List;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ProductShopDTO extends BaseDTO{
	
	
	private String merchantName;
	
	private List<ProductShowDTO> showItem;


	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public List<ProductShowDTO> getShowItem() {
		return showItem;
	}

	public void setShowItem(List<ProductShowDTO> showItem) {
		this.showItem = showItem;
	}
	
	
	

}
