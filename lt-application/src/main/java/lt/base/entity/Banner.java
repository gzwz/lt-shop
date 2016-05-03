package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;






import lt.base.command.CreateBannerCommand;
import lt.base.command.ModifyBannerCommand;

import org.hibernate.annotations.DynamicUpdate;


@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "BANNER")
public class Banner extends StringIdBaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 图片
	 */
	@Column(name = "image", length = 255)
	private String image;
	/**
	 * 路径
	 */
	@Column(name = "url", length = 100)
	private String url;
	/**
	 * 排序
	 */
	@Column(name = "sort", columnDefinition = M.NUM_COLUMN)
	private Integer sort;
	/**
	 * 标题
	 */
	@Column(name = "title", length = 50)
	private String title;
	/**
	 * 1，首页 2，二级页面 3，三级页面 4，广告页面
	 */
	@Column(name = "type", columnDefinition = M.NUM_COLUMN)
	private Integer type;
	
	
	public void create(CreateBannerCommand command){
		
		setId(UUIDGenerator.getUUID());
		
		setImage(command.getImage());
		setTitle(command.getTitle());
		setSort(command.getSort());
		setUrl(command.getUrl());
		setType(command.getType());
		
	}
	
	
	public void modify(ModifyBannerCommand command){
		
		setImage(command.getImage());
		setTitle(command.getTitle());
		setSort(command.getSort());
		setUrl(command.getUrl());
		setType(command.getType());
	}
	
	
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
