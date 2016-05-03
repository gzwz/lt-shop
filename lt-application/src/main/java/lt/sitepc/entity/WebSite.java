package lt.sitepc.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;






import lt.sitepc.command.CreateWebSiteCommand;

import lt.sitepc.command.ModifyWebSiteCommand;

/**
 * 网站设置
 */
import org.hibernate.annotations.DynamicUpdate;


@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "WEBSITE")
public class WebSite extends StringIdBaseEntity{
	
	/**
	 * 键
	 */
	@Column(name = "key_name", length = 50)
	private String key;
	
	
	/**
	 * 值
	 */
	@Column(name = "value_name", columnDefinition=M.TEXT_COLUMN)
	private String value;
	
	/**
	 * 名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	
	
	 /**
     * 配置输入的类型 1:文本输入 2:下拉框输入 3:图片上传 4:编辑器
     */
    @Column(name = "input_type",columnDefinition = M.NUM_COLUMN)
    private Integer inputType;
	

    public void create(CreateWebSiteCommand command){
    	
    	setId(UUIDGenerator.getUUID());
    	
    	setKey(command.getKey());
    	setName(command.getName());
    	setValue(command.getValue());
    	setInputType(command.getInputType());
    	
    }
    
    public void modify(ModifyWebSiteCommand command){
    	setValue(command.getValue());
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
