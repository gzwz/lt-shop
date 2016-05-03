package lt.product.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.merchant.entity.Merchant;
import lt.product.command.CreateProductCommand;
import lt.product.command.ModifyProductCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCT")
public class Product extends StringIdBaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id", nullable = false)
	private ProductCategory productCategory;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productBrand_id", nullable = false)
	private ProductBrand productBrand;
	
	
	/**
	 * 该件商品所在的商家有那些
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "product_screeningCondition", joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "screeningCondition_id", referencedColumnName = "ID") })
	private Set<ScreeningCondition> screeningConditions;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="product")
	private List<ProductExterior> productExterior;
	
	
	/**
	 * 展示信息
	 */
	
	private ProductShowInfo showInfo;


	/**
	 * 状态
	 */
	private String status;

	public final static String STATUS_CREATED = "create";
	public final static String STATUS_ENABLE = "enable";
	public final static String STATUS_DISABLE = "disable";
	public final static String STATUS_REMOVE = "remove";

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 上架时间
	 */
	private Date enableDate;
	
	
	
	public void create(CreateProductCommand command,Merchant merchant,ProductCategory productCategory,ProductBrand productBrand,Set<ScreeningCondition> screeningConditions){
		
		setId(UUIDGenerator.getUUID());
		
		setShowInfo(new ProductShowInfo());
		getShowInfo().setTitleImage(command.getTitleImage());
		getShowInfo().setIntro(command.getIntro());
		getShowInfo().setLowestPrice(command.getLowestPrice());
		getShowInfo().setName(command.getName());
		getShowInfo().setOriginalPrice(command.getOriginalPrice());
		getShowInfo().setHot(false);
		
		setStatus(STATUS_CREATED);
		setCreateDate(new Date());
		setProductBrand(productBrand);
		setProductCategory(productCategory);
		setMerchant(merchant);
		setScreeningConditions(screeningConditions);
	}
	
	public void modify(ModifyProductCommand command){
		
		setShowInfo(new ProductShowInfo());
		getShowInfo().setTitleImage(command.getTitleImage());
		getShowInfo().setIntro(command.getIntro());
		getShowInfo().setLowestPrice(command.getLowestPrice());
		getShowInfo().setName(command.getName());
		getShowInfo().setOriginalPrice(command.getOriginalPrice());
	}
	
	public void enable() {
		setStatus(STATUS_ENABLE);
		setEnableDate(new Date());
	}

	public void disable() {
		setStatus(STATUS_DISABLE);
	}
	
	
	public void t() {
		getShowInfo().setHot(true);
	}

	public void f() {
		getShowInfo().setHot(false);
	}


	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}


	public ProductBrand getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}

	public ProductShowInfo getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(ProductShowInfo showInfo) {
		this.showInfo = showInfo;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Set<ScreeningCondition> getScreeningConditions() {
		return screeningConditions;
	}

	public void setScreeningConditions(Set<ScreeningCondition> screeningConditions) {
		this.screeningConditions = screeningConditions;
	}

	public List<ProductExterior> getProductExterior() {
		return productExterior;
	}

	public void setProductExterior(List<ProductExterior> productExterior) {
		this.productExterior = productExterior;
	}

	
}
