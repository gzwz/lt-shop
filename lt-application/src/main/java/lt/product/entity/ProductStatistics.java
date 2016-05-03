package lt.product.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lt.product.command.CreateProductCommand;
import lt.product.command.ModifyProductCommand;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 详情
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "product_statistics")
public class ProductStatistics extends StringIdBaseEntity{

	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=Product.class)
	@JoinColumn(name = "product_id")
	private Product product;
	
	/**
	 * 移动端商品详情
	 */
	@Column(name = "mobile_detail", columnDefinition = M.TEXT_COLUMN)
	private String mobileDetail;

	/**
	 * PC端商品详情
	 */
	@Column(name = "pc_detail", columnDefinition = M.TEXT_COLUMN)
	private String pcDetail;
	
	/**
	 * 累计销量
	 */
	@Column(name = "total_sale_num", length = 8)
	private Integer totalSaleNum;
	
	public void create(CreateProductCommand command,Product product){
		
		setId(UUIDGenerator.getUUID());
		
		setMobileDetail(command.getMobileDetail());
		setPcDetail(command.getPcDetail());
		setProduct(product);
		setTotalSaleNum(0);
	}
	
	public void modify(ModifyProductCommand command,Product product){
		
		setMobileDetail(command.getMobileDetail());
		setPcDetail(command.getPcDetail());
		setProduct(product);
	}

	public Integer getTotalSaleNum() {
		return totalSaleNum;
	}

	public void setTotalSaleNum(Integer totalSaleNum) {
		this.totalSaleNum = totalSaleNum;
	}

	public String getMobileDetail() {
		return mobileDetail;
	}

	public void setMobileDetail(String mobileDetail) {
		this.mobileDetail = mobileDetail;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
