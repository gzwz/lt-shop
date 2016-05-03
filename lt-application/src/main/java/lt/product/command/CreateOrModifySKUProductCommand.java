package lt.product.command;

import gzlazypack.common.component.BaseCommand;



/**
 * 根据是否传id，来判断这是新增还是修改
 * 
 *
 */
@SuppressWarnings("serial")
public class CreateOrModifySKUProductCommand extends BaseCommand {

	private String productId;

	/**
	 * 库存数，如果不想更新库存传null
	 */
	private Integer storeNum;

	/**
	 * 售价
	 */
	private Double price;


	public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	

}
