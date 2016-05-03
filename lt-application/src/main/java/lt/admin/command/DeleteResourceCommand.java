/**
 *
 */

package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class DeleteResourceCommand extends BaseCommand {

	private String resourceId;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
