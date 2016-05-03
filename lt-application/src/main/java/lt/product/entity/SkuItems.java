package lt.product.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "sku_items")
public class SkuItems extends StringIdBaseEntity{
	
	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=ProductParameter.class)
	@JoinColumn(name = "parameter_id")
	private ProductParameter parameter;
	
	
	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=Product.class)
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	@Column(name="proporty_name")
	private String proportyName;
	
	
	@Column(name="proporty_value_item")
	private String proportyValueItem;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="parameter_time")
	private Date parameterTime;
	
	
	public void create(String skums,Product product,ProductParameter parameter){
		
		setId(UUIDGenerator.getUUID());
		
		setParameter(parameter);
		setProduct(product);
		setProportyName(parameter.getName());
		setProportyValueItem(skums);
		setCreateTime(new Date());
		setParameterTime(parameter.getCreateDate());
	}

	public ProductParameter getParameter() {
		return parameter;
	}

	public void setParameter(ProductParameter parameter) {
		this.parameter = parameter;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProportyName() {
		return proportyName;
	}

	public void setProportyName(String proportyName) {
		this.proportyName = proportyName;
	}

	public String getProportyValueItem() {
		return proportyValueItem;
	}

	public void setProportyValueItem(String proportyValueItem) {
		this.proportyValueItem = proportyValueItem;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getParameterTime() {
		return parameterTime;
	}

	public void setParameterTime(Date parameterTime) {
		this.parameterTime = parameterTime;
	}
	
}
