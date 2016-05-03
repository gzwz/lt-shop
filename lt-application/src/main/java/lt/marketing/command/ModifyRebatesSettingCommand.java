package lt.marketing.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 修改返利设置
 * 
 *
 */
@SuppressWarnings("serial")
public class ModifyRebatesSettingCommand extends BaseCommand {

	private String domainId;

	private String domainType;

	private Integer level;

	private String type;
	
	public final static String TYPE_FIX = "fix"; // 固定金额
	public final static String TYPE_RATE = "rate"; // 比率，1%填0.01
	
	private Double value;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
