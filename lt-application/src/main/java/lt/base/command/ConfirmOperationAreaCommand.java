package lt.base.command;

import gzlazypack.common.component.BaseCommand;

import java.util.List;

/**
 * 为代理商分配区县
 * 
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class ConfirmOperationAreaCommand extends BaseCommand {

	/**
	 * 该代理商的代理区县，会完全移除原有的区县重写传进来的区县
	 */
	private List<String> areaIds;

	/**
	 * 代理商用户id
	 */
	private String userId;

	public List<String> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<String> areaIds) {
		this.areaIds = areaIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
