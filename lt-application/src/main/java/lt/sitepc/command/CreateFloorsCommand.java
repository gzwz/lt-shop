package lt.sitepc.command;


import lt.sitepc.entity.Floors;
import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateFloorsCommand extends BaseCommand{

	/**
	 * 名称
	 */
	private String name;
	
	private String parentId;
	
	/**
	 * 父类目
	 */
	private Floors parent;
	
	
	private String url;
	
	private Double price;
	
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
	 *首图
	 */
	private String titleImage;
	
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

	public Floors getParent() {
		return parent;
	}

	public void setParent(Floors parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}
