package lt.merchant.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ValidateMerchantCommand extends BaseCommand {

	private String merchantId;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
}
