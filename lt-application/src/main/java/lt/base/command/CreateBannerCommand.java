package lt.base.command;

import gzlazypack.common.component.BaseCommand;

public class CreateBannerCommand extends BaseCommand{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 路径
	 */
	private String url;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 
	 */
	private Integer type;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
