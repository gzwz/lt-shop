package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 禁用虚拟帐户
 * 
 * 
 */
@SuppressWarnings("serial")
public class DisableVirtualAccountCommand extends BaseCommand {

	/**
	 * 帐户id
	 */
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
