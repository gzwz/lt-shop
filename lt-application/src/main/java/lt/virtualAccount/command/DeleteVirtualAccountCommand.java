package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 删除虚拟帐户，有余额和记录时不可删 
 * 
 * 
 */
@SuppressWarnings("serial")
public class DeleteVirtualAccountCommand extends BaseCommand {

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
