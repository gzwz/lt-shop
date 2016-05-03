package lt.product.command;

import java.util.List;

import gzlazypack.common.component.BaseCommand;


/**
 * 添加商品参数
 * 
 * @author wxp
 *
 */
@SuppressWarnings("serial")
public class ModifyProductParameterCommand extends BaseCommand {

	private String productParameterId;
	
	private String productCategoryId;

	private String name;
	
    private List<String> productCatyIds;
	
	private String productCategoryIds;
	

	public String getProductParameterId() {
		return productParameterId;
	}

	public void setProductParameterId(String productParameterId) {
		this.productParameterId = productParameterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public List<String> getProductCatyIds() {
		return productCatyIds;
	}

	public void setProductCatyIds(List<String> productCatyIds) {
		this.productCatyIds = productCatyIds;
	}

	public String getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(String productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}
	
	
	
}
