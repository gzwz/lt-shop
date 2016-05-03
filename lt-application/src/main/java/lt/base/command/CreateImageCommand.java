package lt.base.command;

import gzlazypack.common.component.BaseCommand;

import java.util.Map;

@SuppressWarnings("serial")
public class CreateImageCommand extends BaseCommand {
	
	private String title;

	/**
	 * 不同尺寸图片访问地址 例：{"default":"xxx/xxx.jpg", "big":"xxx/xxx1.jpg",
	 * "small":"xxx/xxx2.jpg}
	 */
	private Map<String, String> specImageMap;

	/**
	 * 图片文件存储信息JSON
	 */
	private String fileInfoJSON;

	/**
	 * 关联的业务对象id
	 */
	private String domainLinkId;

	/**
	 * 关联的业务对象类别
	 */
	private String domainLinkType;

	/**
	 * 关联的业务对象名称
	 */
	private String domainLinkName;

	/**
	 * 显示优先级
	 */
	private Integer sort;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getSpecImageMap() {
		return specImageMap;
	}

	public void setSpecImageMap(Map<String, String> specImageMap) {
		this.specImageMap = specImageMap;
	}

	public String getFileInfoJSON() {
		return fileInfoJSON;
	}

	public void setFileInfoJSON(String fileInfoJSON) {
		this.fileInfoJSON = fileInfoJSON;
	}

	public String getDomainLinkId() {
		return domainLinkId;
	}

	public void setDomainLinkId(String domainLinkId) {
		this.domainLinkId = domainLinkId;
	}

	public String getDomainLinkType() {
		return domainLinkType;
	}

	public void setDomainLinkType(String domainLinkType) {
		this.domainLinkType = domainLinkType;
	}

	public String getDomainLinkName() {
		return domainLinkName;
	}

	public void setDomainLinkName(String domainLinkName) {
		this.domainLinkName = domainLinkName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
