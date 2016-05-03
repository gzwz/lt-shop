package lt.product.command;

import java.util.List;

import gzlazypack.common.component.BaseCommand;

public class ModifyProductBrandCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	private String productBrandId;
	
    private String productCategoryId;
	
    private List<String> productCatyIds;
    
    private String productCategoryIds;
	
	
	/**
	 * 品牌名称
	 */
	private String brandName;
	
	
    private String parentId;
    
    private String signImage;
	
	private Integer sort;
	/**
	 * 拼音
	 */
	private String pinyin;
	

	public String getProductBrandId() {
		return productBrandId;
	}

	public void setProductBrandId(String productBrandId) {
		this.productBrandId = productBrandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public List<String> getProductCatyIds() {
		return productCatyIds;
	}

	public void setProductCatyIds(List<String> productCatyIds) {
		this.productCatyIds = productCatyIds;
	}

	public String getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(String productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}


	

}
