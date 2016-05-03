package lt.product.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 商品上架
 * 
 *
 */
@SuppressWarnings("serial")
public class EnableProductCommand extends BaseCommand {

	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
