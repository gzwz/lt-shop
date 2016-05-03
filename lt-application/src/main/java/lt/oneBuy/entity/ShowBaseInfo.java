package lt.oneBuy.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lt.admin.entity.M;
import lt.base.entity.Image;

@Embeddable
public class ShowBaseInfo {
	
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
	 * 价格
	 */
	private Double price;
	
	/**
	 * 市场价格
	 */
	private Double originalPrice;


	/**
	 * 移动端商品详情
	 */
	@Column(name = "mobile_detail", columnDefinition = M.TEXT_COLUMN)
	private String mobileDetail;

	/**
	 * PC端商品详情
	 */
	@Column(name = "pc_detail", columnDefinition = M.TEXT_COLUMN)
	private String pcDetail;

	/**
	 * 是否热门
	 */
	@Type(type = "yes_no")
	@Column(name = "hot")
    private Boolean hot;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Map<Integer, Image> getImageMap() {
		return imageMap;
	}

	public void setImageMap(Map<Integer, Image> imageMap) {
		this.imageMap = imageMap;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMobileDetail() {
		return mobileDetail;
	}

	public void setMobileDetail(String mobileDetail) {
		this.mobileDetail = mobileDetail;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	
	
	

}
