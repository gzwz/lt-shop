package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 启用虚拟帐户
 * 
 */
@SuppressWarnings("serial")
public class EnableVirtualAccountCommand extends BaseCommand {

	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
