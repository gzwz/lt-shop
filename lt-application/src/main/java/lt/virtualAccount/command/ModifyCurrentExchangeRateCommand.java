package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 设置货币兑换比例
 * 
 * 
 */
@SuppressWarnings("serial")
public class ModifyCurrentExchangeRateCommand extends BaseCommand {

	private String id;

	private Double exchangeOneYuan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getExchangeOneYuan() {
		return exchangeOneYuan;
	}

	public void setExchangeOneYuan(Double exchangeOneYuan) {
		this.exchangeOneYuan = exchangeOneYuan;
	}

}
