package lt.product.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.oneBuy.entity.OneBuy;

import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.JSON;


/**
 * 商品快照，每次商品上架时增加一条
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PRODUCT_SNAPSHOT")
public class ProductSnapshot extends StringIdBaseEntity {

	/**
	 * 商品id
	 */
	@Column(name = "PRODUCT_ID", length = 64)
	private String productId;

	/**
	 * 商品实体的整体JSON
	 */
	@Column(name = "SNAPSHOT_JSON", columnDefinition = M.TEXT_COLUMN)
	private String snapshotJSON;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	/**
	 * 普通商品创建的快照
	 * @param product
	 */
	public void create(Product product){
		setId(UUIDGenerator.getUUID());
		setCreateDate(new Date());
		setProductId(product.getId());
		setSnapshotJSON(JSON.toJSONString(product));
	}
	
	/**
	 * 一元购商品创建的快照
	 * @param product
	 */
	public void oneBuyCreate(OneBuy cloundPurchase){
		setId(UUIDGenerator.getUUID());
		setCreateDate(new Date());
		setProductId(cloundPurchase.getId());
		setSnapshotJSON(JSON.toJSONString(cloundPurchase));
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSnapshotJSON() {
		return snapshotJSON;
	}

	public void setSnapshotJSON(String snapshotJSON) {
		this.snapshotJSON = snapshotJSON;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
