package lt.oneBuy.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lt.base.dto.BaseDTO;
import lt.oneBuy.entity.OneBuy;

@SuppressWarnings("serial")
public class OneBuyDTO extends BaseDTO{

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
	 * 最低价
	 */
	private Double lowestPrice;

	/**
	 * 市价
	 */
	private Double originalPrice;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 上架时间
	 */
	private Date enableDate;
	
	public static List<OneBuyDTO> domainToDTO(List<OneBuy> cloundPurchases) {

		if (CollectionUtils.isEmpty(cloundPurchases))
			return new ArrayList<>();

		List<OneBuyDTO> list = new ArrayList<>();
		OneBuyDTO dto = null;
		for (OneBuy product : cloundPurchases) {
			dto = new OneBuyDTO();
			dto.setId(product.getId());
		/*	dto.setName(product.getShowInfo().getName());
			dto.setTitleImage(product.getShowInfo().getTitleImage());
			dto.setIntro(product.getShowInfo().getIntro());
			dto.setLowestPrice(product.getShowInfo().getLowestPrice());
			dto.setOriginalPrice(product.getShowInfo().getOriginalPrice());
			dto.setStatus(product.getStatus());
			dto.setCreateDate(product.getCreateDate());
			dto.setEnableDate(product.getEnableDate());*/
			list.add(dto);
		}

		return list;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getEnableDate() {
		return enableDate;
	}


	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}
	
}
