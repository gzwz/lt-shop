package lt.product.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyProductParameterValueCommand extends BaseCommand {

	private String productParameterValueId;
	
	private String productId;

	private String productParameterId;

	private String value;

	public String getProductParameterValueId() {
		return productParameterValueId;
	}

	public void setProductParameterValueId(String productParameterValueId) {
		this.productParameterValueId = productParameterValueId;
	}

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
