package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 关闭虚拟帐户，不会再启用
 * 
 * 
 */
@SuppressWarnings("serial")
public class CloseVirtualAccountCommand extends BaseCommand {

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
