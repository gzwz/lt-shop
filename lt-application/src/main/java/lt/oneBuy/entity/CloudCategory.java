package lt.oneBuy.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lt.oneBuy.command.CreateCloudCategoryCommand;
import lt.oneBuy.command.ModifyCloudCategoryCommand;
import lt.product.entity.ProductCategory;
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCTCATEGORY")
public class CloudCategory extends StringIdBaseEntity{

	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_cloud_category_id")
	private CloudCategory parent;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.ALL })
	private Set<ProductCategory> children;
	
	@Column(name = "sort", length = 512)
	private Integer sort;
	
	
	public void create(CreateCloudCategoryCommand command,CloudCategory parent){
		
		setId(UUIDGenerator.getUUID());
		
		setName(command.getName());
		setParent(parent);
		setSort(command.getSort());
		
	}
	
	
	public void modify(ModifyCloudCategoryCommand command ,CloudCategory parent){
		
		setName(command.getName());
		setParent(parent);
		setSort(command.getSort());
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CloudCategory getParent() {
		return parent;
	}

	public void setParent(CloudCategory parent) {
		this.parent = parent;
	}

	public Set<ProductCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
