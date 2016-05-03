package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ValidShoppingCarCommand extends BaseCommand{
	
	private String shoppingCarId;

	public String getShoppingCarId() {
		return shoppingCarId;
	}

	public void setShoppingCarId(String shoppingCarId) {
		this.shoppingCarId = shoppingCarId;
	}
	
}
