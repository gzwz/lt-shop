package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 创建资源
 * 
 * @author yuxiaoxiang
 * 
 */
@SuppressWarnings("serial")
public class CreateResourceCommand extends BaseCommand {

	/**
	 * 资源所属客户端类型
	 */
	private String clientType;

	private String url;

	private String name;

	private String remark;
	
	private String parentId;
	
	private String icon;
	
	private Integer sort;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}
