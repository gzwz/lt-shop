package lt.sitepc.DTO;

import java.util.List;


import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class FloorsDTO extends BaseDTO{
    
	private String name;
	
     private String url;
	
	private Double price;

	
	/**
	 *首图
	 */
	private String titleImage;
	
	private List<FloorsParentDTO> parentList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FloorsParentDTO> getParentList() {
		return parentList;
	}

	public void setParentList(List<FloorsParentDTO> parentList) {
		this.parentList = parentList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	
	
	
}
