package lt.merchant.command;


import gzlazypack.common.component.BaseCommand;

/**
 * 
 * @author wxp
 *
 */
@SuppressWarnings("serial")
public class ModifyMerchantCategoryCommand extends BaseCommand{
	

	private String merchantCategoryId;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父类目
	 */
	private String parentId;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	
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


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public String getMerchantCategoryId() {
		return merchantCategoryId;
	}


	public void setMerchantCategoryId(String merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}
	
}
