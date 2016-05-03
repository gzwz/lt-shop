package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ShoppingItemSubmitCommand extends BaseCommand{
	
	private String totale;
	
	private String shoppingNume;
	
	private String shoppingCarItems;
	
	private String merchantId;


	public String getShoppingNume() {
		return shoppingNume;
	}

	public void setShoppingNume(String shoppingNume) {
		this.shoppingNume = shoppingNume;
	}

	public String getShoppingCarItems() {
		return shoppingCarItems;
	}

	public void setShoppingCarItems(String shoppingCarItems) {
		this.shoppingCarItems = shoppingCarItems;
	}

	public String getTotale() {
		return totale;
	}

	public void setTotale(String totale) {
		this.totale = totale;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
