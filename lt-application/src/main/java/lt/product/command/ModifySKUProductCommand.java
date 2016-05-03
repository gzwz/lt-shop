package lt.product.command;

import lt.product.entity.Product;
import gzlazypack.common.component.BaseCommand;

public class ModifySKUProductCommand extends BaseCommand{
	
	private static final long  serialVersionUID=1L;
	
	private String sKUProductId;
	
	
	/**
	 * 数量
	 */
	private Integer number;
	
	/**
	 * 售价
	 */
	private Double price;

	public String getsKUProductId() {
		return sKUProductId;
	}

	public void setsKUProductId(String sKUProductId) {
		this.sKUProductId = sKUProductId;
	}


	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
