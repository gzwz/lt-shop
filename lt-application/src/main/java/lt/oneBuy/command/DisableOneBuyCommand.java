package lt.oneBuy.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 商品下架
 */
@SuppressWarnings("serial")
public class DisableOneBuyCommand extends BaseCommand {

	private String oneBuyId;

	public String getOneBuyId() {
		return oneBuyId;
	}

	public void setOneBuyId(String oneBuyId) {
		this.oneBuyId = oneBuyId;
	}
 
}