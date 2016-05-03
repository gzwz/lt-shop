package lt.product.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class AddNumCommand extends BaseCommand{

	/**
	 * 数量
	 */
	private Integer num;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
