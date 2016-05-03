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
public class CreateProductParameterCommand extends BaseCommand {

	private String name;
	
	private String productCategoryId;
	
	private List<String> productCatyIds;
	
	private String productCategoryIds;
	

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


	public String getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(String productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}

	public List<String> getProductCatyIds() {
		return productCatyIds;
	}

	public void setProductCatyIds(List<String> productCatyIds) {
		this.productCatyIds = productCatyIds;
	}
	
	
	
}
