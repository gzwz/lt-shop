package lt.sitepc.DTO;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class FloorsParentDTO extends BaseDTO {

	private String name;

	private String url;

	private Double price;

	/**
	 * 首图
	 */
	private String titleImage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
