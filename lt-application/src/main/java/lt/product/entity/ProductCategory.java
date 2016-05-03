package lt.product.entity;


import java.util.Date;
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

import lt.product.command.CreateProductCategoryCommand;
import lt.product.command.ModifyProductCategoryCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCTCATEGORY")
public class ProductCategory extends StringIdBaseEntity{

	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_product_category_id")
	private ProductCategory parent;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.ALL })
	private Set<ProductCategory> children;
	
	@Column(name = "sort", length = 512)
	private Integer sort;
	
	@Column(name = "create_time")
	private Date createTime;
	
   public void create(CreateProductCategoryCommand command,ProductCategory parent){
		
		setId(UUIDGenerator.getUUID());
		setName(command.getName());
		setParent(parent);
		setCreateTime(new Date());
		setSort(command.getSort());
	}

	
	public void modify(ModifyProductCategoryCommand command,ProductCategory parent){
		
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

	public ProductCategory getParent() {
		return parent;
	}


	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}


	public Set<ProductCategory> getChildren() {
		return children;
	}


	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
