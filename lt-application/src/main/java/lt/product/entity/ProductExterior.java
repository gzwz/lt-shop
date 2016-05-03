package lt.product.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.product.command.CreateProductExteriorCommand;
import lt.product.command.ModifyProductExteriorCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCTEXTERIOR")
public class ProductExterior extends StringIdBaseEntity{

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	/**
	 * 外观名称
	 */
	@Column(name = "name", length = 100)
	private String name;
	
	/**
	 * 类型
	 */
	private String type;

	public final static String TYPE_EXTERIOR = "EXTERIOR";
	public final static String TYPE_DRIVERCAB = "DRIVERCAB";
	public final static String TYPE_TERRITORY = "TERRITORY";
	
	
    public void create(CreateProductExteriorCommand command,Product product){
    	
    	setId(UUIDGenerator.getUUID());
    	
    	setName(command.getName());
    	setType(command.getType());
    	setProduct(product);
    }
    
    
    public void modfiy(ModifyProductExteriorCommand command,Product product){
    	
    	setName(command.getName());
    	setType(command.getType());
    	setProduct(product);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
