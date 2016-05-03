package lt.product.entity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;



import org.hibernate.annotations.Type;

import lt.base.entity.Image;
@Embeddable
public class ProductShowInfo{

	/**
	 * 商品名称
	 */
	@Column(name = "name", length = 100)
	private String name;
	
	/**
	 * 商品首图
	 */
	@Column(name = "title_image", length = 500)
	private String titleImage;

	/**
	 * 商品图片
	 */
	@Transient
	private Map<Integer, Image> imageMap;

	/**
	 * 商品简介
	 */
	@Column(name = "intro", columnDefinition = M.TEXT_COLUMN)
	private String intro;

	/**
	 * 最低价
	 */
	private Double lowestPrice;

	/**
	 * 市价
	 */
	private Double originalPrice;

	/**
	 * 是否热门
	 */
	@Type(type = "yes_no")
	@Column(name = "hot")
    private Boolean hot;
	
	/**
	 * 按尺寸需要取商品首图 default是默认尺寸
	 * 
	 * @param imageSpecKey
	 * @return
	 */
	public String getTitleImageUrl(String imageSpecKey) {
		if (imageMap != null) {
			Image image = getImageMap().get(0);
			if (image != null) {
				return image.getSpecImageMap().get(imageSpecKey);
			}
		}
		return "";
	}

	/**
	 * 初始化图片
	 * @param images
	 */
	public void setImageMap(List<Image> images) {
		setImageMap(new HashMap<Integer, Image>());
		for (Image image : images) {
			getImageMap().put(image.getSort(), image);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Map<Integer, Image> getImageMap() {
		return imageMap;
	}

	public void setImageMap(Map<Integer, Image> imageMap) {
		this.imageMap = imageMap;
	}


	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	
	

}
