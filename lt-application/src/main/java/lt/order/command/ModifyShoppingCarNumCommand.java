package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyShoppingCarNumCommand extends BaseCommand{
	
	private String shoppingCarId;
	
	private Integer num;

	public String getShoppingCarId() {
		return shoppingCarId;
	}

	public void setShoppingCarId(String shoppingCarId) {
		this.shoppingCarId = shoppingCarId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	

}
