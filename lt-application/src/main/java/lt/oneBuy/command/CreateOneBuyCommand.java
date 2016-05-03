package lt.oneBuy.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;

import lt.base.command.CreateImageCommand;

@SuppressWarnings("serial")
public class CreateOneBuyCommand extends BaseCommand{
	
	
	private String cloudCategoryId;
	
	private String cloudBrandId;
	
	private String images;
	
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 参与人数
	 */
	private Integer partakeCount;
	/**
	 * 需要人数
	 */
	private Integer totalCount;
	/**
	 * 剩余数量
	 */
	private Integer residueCount;
	
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


	/**
	 * 市场价
	 */
	private Double originalPrice;
	
	/**
	 * 售价
	 */
	private Double price;
	
	/**
	 * 创建多张图片
	 */
	private List<CreateImageCommand> createImageCommandList;
	
	
	/**
	 * 数量
	 */
	private Integer number;
	
	
	/**
	 * 移动端商品详情
	 */
	private String mobileDetail;

	/**
	 * PC端商品详情
	 */
	private String pcDetail;
	
	


	public String getCloudCategoryId() {
		return cloudCategoryId;
	}

	public void setCloudCategoryId(String cloudCategoryId) {
		this.cloudCategoryId = cloudCategoryId;
	}

	public String getCloudBrandId() {
		return cloudBrandId;
	}

	public void setCloudBrandId(String cloudBrandId) {
		this.cloudBrandId = cloudBrandId;
	}

	public List<CreateImageCommand> getCreateImageCommandList() {
		return createImageCommandList;
	}

	public void setCreateImageCommandList(
			List<CreateImageCommand> createImageCommandList) {
		this.createImageCommandList = createImageCommandList;
	}

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

 
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public Integer getPartakeCount() {
		return partakeCount;
	}

	public void setPartakeCount(Integer partakeCount) {
		this.partakeCount = partakeCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getResidueCount() {
		return residueCount;
	}

	public void setResidueCount(Integer residueCount) {
		this.residueCount = residueCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}