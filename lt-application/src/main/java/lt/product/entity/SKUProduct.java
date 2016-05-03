package lt.product.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;














import lt.product.command.AddNumCommand;
import lt.product.command.CreateOrModifySKUProductCommand;
import lt.product.command.CreateProductCommand;
import lt.product.command.ModifySKUProductCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "sku_product")
public class SKUProduct extends StringIdBaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	
	/**
	 * 数量
	 */
	@Column(name = "num", length = 16)
	private Integer number;
	
	/**
	 * 售价
	 */
	@Column(name = "PRICE", columnDefinition = M.MONEY_COLUMN)
	private Double price;
   
	/**
	 * 格式：白色，车型，尺寸
	 */
	@Column(name = "sku_spec_info")
	private String skuSpecInfo;
	
	@Type(type = "yes_no")
	@Column(name = "is_hide")
	private Boolean hide;
	
	
	public void create(CreateProductCommand command,Product product){
		
		setId(UUIDGenerator.getUUID());
		
		setNumber(command.getNumber());
		setPrice(command.getPrice());
		setProduct(product);
		setSkuSpecInfo(command.getSkuSpecInfo());
		setHide(false);
		
	}
	
	public void modify(ModifySKUProductCommand command){
		
		setNumber(command.getNumber());
		setPrice(command.getPrice());
	}
	
	public void modify(CreateOrModifySKUProductCommand command) {
		setPrice(command.getPrice());
		setHide(false);
	}
	
	public void addNum(AddNumCommand command) {
		Integer temp = (getNumber() + command.getNum());
		if (temp < 0) {
			setNumber(0);
		} else {
			setNumber(temp);
		}
	}
	
	public void hide() {
		setHide(true);
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Boolean getHide() {
		return hide;
	}


	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}
	
	
}
