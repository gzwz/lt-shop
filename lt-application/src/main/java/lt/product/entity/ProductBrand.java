package lt.product.entity;

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

import lt.product.command.CreateProductBrandCommand;
import lt.product.command.ModifyProductBrandCommand;
import lt.utils.PinyingUtil;

import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCTBRAND")
public class ProductBrand extends StringIdBaseEntity{

	/**
	 * 品牌名称
	 */
	@Column(name = "brand_name", length = 64)
	private String brandName;
	
	/**
	 * 拼音
	 */
	@Column(name = "pinyin", length = 64)
	private String pinyin;
	
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_product_brand_id")
	@JSONField(serialize =false)
	private ProductBrand parent;
	
	@Column(name = "sign_image", length = 1000)
	private String signImage;
	
	@Column(name = "sort", length = 512)
	private Integer sort;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.ALL })
	@JSONField(serialize =false)
	private Set<ProductBrand> children;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "productBrand_productCategory", joinColumns = { @JoinColumn(name = "productBrand_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "productCategory_id", referencedColumnName = "ID") })
	private Set<ProductCategory> productCategorys;
	
	
	public void create(CreateProductBrandCommand command,ProductBrand parent,Set<ProductCategory> productCategorys){
		
		setId(UUIDGenerator.getUUID());
		
		setBrandName(command.getBrandName());
		setPinyin(PinyingUtil.getHeadByString(command.getBrandName())[0]);
		setProductCategorys(productCategorys);
		setSort(command.getSort());
		setSignImage(command.getSignImage());
		setParent(parent);
	}
	
	public void modify(ModifyProductBrandCommand command,ProductBrand parent,Set<ProductCategory> productCategorys){
		
		setBrandName(command.getBrandName());
		setPinyin(PinyingUtil.getHeadByString(command.getBrandName())[0]);
		setProductCategorys(productCategorys);
		setSort(command.getSort());
		setSignImage(command.getSignImage());
		setParent(parent);
	}
	

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Set<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(Set<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public ProductBrand getParent() {
		return parent;
	}

	public void setParent(ProductBrand parent) {
		this.parent = parent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<ProductBrand> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductBrand> children) {
		this.children = children;
	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}


	
}
