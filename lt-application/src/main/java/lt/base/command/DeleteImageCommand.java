package lt.base.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class DeleteImageCommand extends BaseCommand {

	private String imageId;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
