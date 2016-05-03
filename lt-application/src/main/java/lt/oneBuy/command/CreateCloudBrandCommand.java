package lt.oneBuy.command;

import java.util.List;

import gzlazypack.common.component.BaseCommand;

public class CreateCloudBrandCommand extends BaseCommand {

	private static final long serialVersionUID = 1L;

	private String cloudCategoryId;

	private List<String> cloudCategoryIds;

	/**
	 * 品牌名称
	 */
	private String brandName;

	private String parentId;

	private Integer sort;

	/**
	 * 拼音
	 */
	private String pinyin;

	public String getCloudCategoryId() {
		return cloudCategoryId;
	}

	public void setCloudCategoryId(String cloudCategoryId) {
		this.cloudCategoryId = cloudCategoryId;
	}

	public List<String> getCloudCategoryIds() {
		return cloudCategoryIds;
	}

	public void setCloudCategoryIds(List<String> cloudCategoryIds) {
		this.cloudCategoryIds = cloudCategoryIds;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
