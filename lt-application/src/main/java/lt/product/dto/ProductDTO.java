package lt.product.dto;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import lt.base.dto.BaseDTO;
import lt.product.entity.Product;

@SuppressWarnings("serial")
public class ProductDTO extends BaseDTO{

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
	
	private Boolean hot;
	
	public static List<ProductDTO> domainToDTO(List<Product> products) {

		if (CollectionUtils.isEmpty(products))
			return new ArrayList<>();

		List<ProductDTO> list = new ArrayList<>();
		ProductDTO dto = null;
		for (Product product : products) {
			dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getShowInfo().getName());
			dto.setTitleImage(product.getShowInfo().getTitleImage());
			dto.setIntro(product.getShowInfo().getIntro());
			dto.setLowestPrice(product.getShowInfo().getLowestPrice());
			dto.setOriginalPrice(product.getShowInfo().getOriginalPrice());
			dto.setStatus(product.getStatus());
			dto.setCreateDate(product.getCreateDate());
			dto.setEnableDate(product.getEnableDate());
			dto.setHot(product.getShowInfo().getHot());
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


	public Boolean getHot() {
		return hot;
	}


	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	
	
	
}
