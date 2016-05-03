package lt.product.entity;

import java.util.List;
import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.product.command.CreateScreeningConditionCommand;
import lt.product.command.ModifyScreeningConditionCommand;

import org.hibernate.annotations.DynamicUpdate;
/**
 * 筛选条件
 * @author wxp
 *
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "SCREENINGCONDITION")
public class ScreeningCondition extends StringIdBaseEntity{
	
	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_Screening_condition_id")
	private ScreeningCondition parent;
	
	@Column(name = "sort", length = 512)
	private Integer sort;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.ALL })
	private Set<ScreeningCondition> children;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "screeningCondition_productCategory", joinColumns = { @JoinColumn(name = "screeningCondition_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "productCategory_id", referencedColumnName = "ID") })
	private Set<ProductCategory> productCategorys;
	
	/**
	 * 产品
	 */
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="screeningConditions")
	private List<Product> products;
	
	
	
	public void create(CreateScreeningConditionCommand command,ScreeningCondition parent,Set<ProductCategory> productCategorys){
		
		setId(UUIDGenerator.getUUID());
		
		setParent(parent);
		setSort(command.getSort());
		setName(command.getName());
		setProductCategorys(productCategorys);
		
	}
	
   public void modify(ModifyScreeningConditionCommand command,ScreeningCondition parent,Set<ProductCategory> productCategorys){
		
		
		setParent(parent);
		setSort(command.getSort());
		setName(command.getName());
		setProductCategorys(productCategorys);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScreeningCondition getParent() {
		return parent;
	}

	public void setParent(ScreeningCondition parent) {
		this.parent = parent;
	}

	public Set<ScreeningCondition> getChildren() {
		return children;
	}

	public void setChildren(Set<ScreeningCondition> children) {
		this.children = children;
	}

	public Set<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(Set<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
