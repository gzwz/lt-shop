package lt.content.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class DeleteNavigationItemCommand extends BaseCommand {

	private String navigationItemId;

	public String getNavigationItemId() {
		return navigationItemId;
	}

	public void setNavigationItemId(String navigationItemId) {
		this.navigationItemId = navigationItemId;
	}

}
