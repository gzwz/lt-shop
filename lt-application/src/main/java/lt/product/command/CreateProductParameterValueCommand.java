package lt.product.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 创建商品参数值
 * 
 * @author wxp
 *
 */
@SuppressWarnings("serial")
public class CreateProductParameterValueCommand extends BaseCommand {

	private String productId;

	private String productParameterId;

	private String value;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(String productParameterId) {
		this.productParameterId = productParameterId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
