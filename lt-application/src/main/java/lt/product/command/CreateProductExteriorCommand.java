package lt.product.command;


import java.util.List;

import lt.base.command.CreateImageCommand;
import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateProductExteriorCommand extends BaseCommand{

	
	private String productId;
	
	/**
	 * 外观名称
	 */
	private String name;
	
	/**
	 * 类型
	 */
	private String type;
	
	private String images;
	
	/**
	 * 创建多张图片
	 */
	private List<CreateImageCommand> createImageCommandList;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<CreateImageCommand> getCreateImageCommandList() {
		return createImageCommandList;
	}

	public void setCreateImageCommandList(
			List<CreateImageCommand> createImageCommandList) {
		this.createImageCommandList = createImageCommandList;
	}
	
	
	
}
