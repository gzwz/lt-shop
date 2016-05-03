package lt.order.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.order.command.CreateShoppingCarItemCommand;
import lt.order.command.ModifyShoppingCarItemCommand;
import lt.order.command.ModifyShoppingCarNumCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


/**
 * 购物车
 * 
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "SHOPPING_CAR_ITEM")
public class ShoppingCarItem extends StringIdBaseEntity {
	
	/**
	 * 所属购物车
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SHOPPING_CAR_ID", nullable = false)
	private ShoppingCar shoppingCar;
	
	/**
	 * sku商品id，SKUCommonProduct的id
	 */
	@Column(name = "sku_product_id", length = 64)
	private String skuProductId;

	/**
	 * 规格颜色，如“灰色，车型”
	 */
	@Column(name = "sku_spec_info")
	private String skuSpecInfo;

	/**
	 * 商品名称
	 */
	@Column(name = "product_name", length = 256)
	private String productName;
	
	
	/**
	 *分类名称
	 */
	@Column(name = "productCatory_name", length = 256)
	private String productCatoryName;

	/**
	 * 商品图片链接
	 */
	@Column(name = "title_image_url", length = 512)
	private String titleImageUrl;

	/**
	 * 所选数量
	 */
	@Column(name = "num", length = 4)
	private Integer num;

	/**
	 * 商品原价，未计算任何折扣和优惠的商品售价 取值SKUCommonProduct.price
	 */
	@Column(name = "original_price", columnDefinition = M.MONEY_COLUMN)
	private Double originalPrice;

	/**
	 * 商品是否有效
	 */
	@Type(type = "yes_no")
	@Column(name = "valid")
	private Boolean valid;

	/**
	 * 是否被选中
	 */
	@Type(type = "yes_no")
	@Column(name = "selected")
	private Boolean selected = true;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	

	/**
	 * 增加数量
	 * 
	 * @param i
	 * @return
	 */
	public int addNum(int i) {
		int newNum = getNum() + i;
		if (newNum < 0) {
			setNum(0);
		} else {
			setNum(newNum);
		}
		return getNum();
	}
	
	
	public void create(CreateShoppingCarItemCommand command,ShoppingCar shoppingCar){
		
		setId(UUIDGenerator.getUUID());
		
		setSkuProductId(command.getSkuProductId());
		setSkuSpecInfo(command.getSkuSpecInfo());
		setProductName(command.getProductName());
		setTitleImageUrl(command.getTitleImageUrl());
		setNum(command.getNum());
		setOriginalPrice(command.getOriginalPrice());
		setProductCatoryName(command.getProductCatoryName());
		setValid(true);
		setSelected(true);
		setCreateTime(new Date());
		
		setShoppingCar(shoppingCar);
	}
	
   public void modify(ModifyShoppingCarItemCommand command,ShoppingCar shoppingCar){
		
		
		setSkuProductId(command.getSkuProductId());
		setSkuSpecInfo(command.getSkuSpecInfo());
		setProductName(command.getProductName());
		setTitleImageUrl(command.getTitleImageUrl());
		setNum(command.getNum());
		setOriginalPrice(command.getOriginalPrice());
		setValid(true);
		setSelected(true);
		setUpdateTime(new Date());
		
		setShoppingCar(shoppingCar);
	}


   public void updateNum(ModifyShoppingCarNumCommand command){
	   setNum(command.getNum());
	   setUpdateTime(new Date());
   }
   
   public void validate(){
      
	   setValid(false);
   }
   
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitleImageUrl() {
		return titleImageUrl;
	}

	public void setTitleImageUrl(String titleImageUrl) {
		this.titleImageUrl = titleImageUrl;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSkuProductId() {
		return skuProductId;
	}

	public void setSkuProductId(String skuProductId) {
		this.skuProductId = skuProductId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public String getProductCatoryName() {
		return productCatoryName;
	}


	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}


	public ShoppingCar getShoppingCar() {
		return shoppingCar;
	}


	public void setShoppingCar(ShoppingCar shoppingCar) {
		this.shoppingCar = shoppingCar;
	}
	
	
}
