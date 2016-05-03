package lt.sitepc.entity;

import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.sitepc.command.CreateFloorsCommand;
import lt.sitepc.command.ModifyFloorsCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "FLOORS")
public class Floors extends StringIdBaseEntity{
	
	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_floors_id")
	private Floors parent;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.ALL })
	private Set<Floors> children;
	
	/**
	 * 名称
	 */
	@Column(name = "url", length = 500)
	private String url;
	
	@Column(name = "price")
	private Double price;

	
	/**
	 *首图
	 */
	@Column(name = "title_image", length = 500)
	private String titleImage;
	
	@Column(name = "sort", length = 512)
	private Integer sort;
	
	public void create(CreateFloorsCommand command,Floors parent){
		
		setId(UUIDGenerator.getUUID());
		
		setName(command.getName());
		setParent(parent);
		setTitleImage(command.getTitleImage());
		setPrice(command.getPrice());
		setSort(command.getSort());
		setUrl(command.getUrl());
		
	}
	
public void modify(ModifyFloorsCommand command,Floors parent){
		
		
		setName(command.getName());
		setParent(parent);
		setTitleImage(command.getTitleImage());
		setPrice(command.getPrice());
		setSort(command.getSort());
		setUrl(command.getUrl());
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Floors getParent() {
		return parent;
	}

	public void setParent(Floors parent) {
		this.parent = parent;
	}

	public Set<Floors> getChildren() {
		return children;
	}

	public void setChildren(Set<Floors> children) {
		this.children = children;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	
}
