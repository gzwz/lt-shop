package lt.oneBuy.command;

import java.util.List;

import lt.base.command.CreateImageCommand;
import gzlazypack.common.component.BaseCommand;

public class ModifyOneBuyCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
    private String id;
    
    private String productStatisticsId;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品首图
	 */
	private String titleImage;

	/**
	 * 商品简介
	 */
	private String intro;
	
	private String images;

	private Double price;

	public Double getPrice() {
		return price;
	}

	/**
	 * 市价
	 */
	private Double originalPrice;
	
	/**
	 * 创建多张图片
	 */
	private List<CreateImageCommand> createImageCommandList;
	
	/**
	 * 移动端商品详情
	 */
	private String mobileDetail;

	/**
	 * PC端商品详情
	 */
	private String pcDetail;
	
	
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}


	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public List<CreateImageCommand> getCreateImageCommandList() {
		return createImageCommandList;
	}

	public void setCreateImageCommandList(
			List<CreateImageCommand> createImageCommandList) {
		this.createImageCommandList = createImageCommandList;
	}


	public String getProductStatisticsId() {
		return productStatisticsId;
	}

	public void setProductStatisticsId(String productStatisticsId) {
		this.productStatisticsId = productStatisticsId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	
}
