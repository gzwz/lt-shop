package lt.order.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;









import lt.order.command.CreateorderFromOneBuyCommand;
import lt.product.entity.ProductSnapshot;
import lt.product.entity.SKUProduct;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 订单中每个具体商品项
 * 
 * @author  
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ORDER_SKU_ITEM")
public class OrderSKUItem extends StringIdBaseEntity {

	@JoinColumn(name = "order_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;

	/**
	 * 商品图片地址
	 */
	@Column(name = "imgage_url", length = 300)
	private String imageUrl;

	/**
	 * sku商品id
	 */
	@Column(name = "sku_product_id")
	private String skuProductId;

	/**
	 * 商品名称
	 */
	@Column(name = "product_name")
	private String productName;

	/**
	 * 规格颜色，如“白色，车型”
	 */
	@Column(name = "sku_spec_info")
	private String skuSpecInfo;

	/**
	 * 库存编码
	 */
	@Column(name = "store_code")
	private String stockId;

	/**
	 * 售价，单价
	 */
	@Column(name = "price", columnDefinition = M.MONEY_COLUMN)
	private Double price;

	/**
	 * 购买数量
	 */
	@Column(name = "num", columnDefinition = M.NUM_COLUMN)
	private Integer num;

	/**
	 * 商品快照id
	 */
	@Column(name = "product_snapshot_id")
	private String productSnapshotId;
	
	/**
	 * 
	 * @param order
	 * @param productSnapshotId2 
	 * @param skuProduct
	 * @param num
	 * @param productSnapshotId
	 * 一元购订单详情
	 */
	public void oneBuyCreate(Order order, CreateorderFromOneBuyCommand buyCommand, ProductSnapshot productSnapshot) {
		// TODO Auto-generated method stub
		setId(UUIDGenerator.getUUID());
		setImageUrl(buyCommand.getTitleImage());
		setNum(buyCommand.getNum());
		setOrder(order);
		setPrice(Double.valueOf(buyCommand.getTotalPrice()));
		setProductName(buyCommand.getSubject());
		setProductSnapshotId(productSnapshot.getId());
		setSkuProductId(productSnapshot.getProductId());
	//	setSkuSpecInfo(skuSpecInfo);
	//	setStockId(stockId);
		
		
		
	}


	public void create(Order order, SKUProduct skuProduct,String titleImageUrl,Integer num,String productSnapshotId) {
		setId(UUIDGenerator.getUUID());

		setPrice(skuProduct.getPrice());
		setProductName(skuProduct.getProduct().getShowInfo().getName());
		setImageUrl(titleImageUrl);
		setNum(num);
		setPrice(skuProduct.getPrice());
		setOrder(order);
		setSkuProductId(skuProduct.getId());
		setProductSnapshotId(productSnapshotId);
		setSkuSpecInfo(skuProduct.getSkuSpecInfo());
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getProductSnapshotId() {
		return productSnapshotId;
	}

	public void setProductSnapshotId(String productSnapshotId) {
		this.productSnapshotId = productSnapshotId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}



}
