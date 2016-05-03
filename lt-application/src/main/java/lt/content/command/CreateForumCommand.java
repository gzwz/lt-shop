package lt.content.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class CreateForumCommand extends BaseCommand {

	/**
	 * 版块名称
	 */
	private String name;

	/**
	 * 板块类型
	 */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
