package lt.sitepc.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyWebSiteCommand extends BaseCommand{

	private String webSiteId;
	
	/**
	 * 键
	 */
	private String key;
	
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 名称
	 */
	private String name;
	
	
	 /**
     * 配置输入的类型 0:文本输入 1:下拉框输入 2:图片上传 3:编辑器
     */
    private Integer inputType;


	public String getWebSiteId() {
		return webSiteId;
	}


	public void setWebSiteId(String webSiteId) {
		this.webSiteId = webSiteId;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getInputType() {
		return inputType;
	}


	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
    
}
