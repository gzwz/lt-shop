package lt.product.command;

import java.util.List;

import gzlazypack.common.component.BaseCommand;

public class CreateScreeningConditionCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	/**
	 * 名称
	 */
	private String name;
	
	private String parentId;
	
	private Integer sort;
	
   private List<String> productCatyIds;
	
	private String productCategoryIds;
	
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	
	
}
