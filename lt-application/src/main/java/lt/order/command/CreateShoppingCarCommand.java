package lt.order.command;


import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateShoppingCarCommand extends BaseCommand{

	
	/**
	 * 所属用户
	 */
	private String userId;
	

	/**
	 * 购物车归属商信息
	 */
	private String operationMerchantId;

	private String productId;
	
	
	private String skuSpecInfo;
	
	private int num;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String productCatoryName;

	public String getOperationMerchantId() {
		return operationMerchantId;
	}


	public void setOperationMerchantId(String operationMerchantId) {
		this.operationMerchantId = operationMerchantId;
	}



	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}


	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getProductCatoryName() {
		return productCatoryName;
	}


	public void setProductCatoryName(String productCatoryName) {
		this.productCatoryName = productCatoryName;
	}
	
	
}
