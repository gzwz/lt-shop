package lt.product.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.product.command.CreateProductParameterCommand;
import lt.product.command.ModifyProductParameterCommand;

import org.hibernate.annotations.DynamicUpdate;


/**
 * 产品参数
 * @author wxp
 *
 */
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCT_PARAMETER")
@DynamicUpdate
@SuppressWarnings("serial")
public class ProductParameter extends StringIdBaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "productParameter_productCategory", joinColumns = { @JoinColumn(name = "productParameter_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "productCategory_id", referencedColumnName = "ID") })
	private Set<ProductCategory> productCategorys;
	
	
	/**
	 * 创建时间
	 * 
	 */
	@Column(name = "create_date")
	private Date createDate;
	
	 /**
     * 配置输入的类型 1:下拉框 2:图标 3:多选框4，单选框 
     */
   /* @Column(name = "input_type",columnDefinition = M.NUM_COLUMN)
    private Integer inputType;*/
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="parameter")
	private List<ProductParameterValue> productParameterValues;

	public void create(CreateProductParameterCommand command,Set<ProductCategory> productCategorys) {
		setId(UUIDGenerator.getUUID());
		
		setName(command.getName());
		setProductCategorys(productCategorys);
		setCreateDate(new Date());
		
	}
	
	public void modify(ModifyProductParameterCommand command,Set<ProductCategory> productCategorys) {
		
		setName(command.getName());
		setProductCategorys(productCategorys);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(Set<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public List<ProductParameterValue> getProductParameterValues() {
		return productParameterValues;
	}

	public void setProductParameterValues(
			List<ProductParameterValue> productParameterValues) {
		this.productParameterValues = productParameterValues;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
