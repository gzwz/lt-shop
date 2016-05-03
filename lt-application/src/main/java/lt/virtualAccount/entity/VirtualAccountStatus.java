package lt.virtualAccount.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

/**
 * 帐户状态
 * 
 * 
 */
@Embeddable
public class VirtualAccountStatus {

	/**
	 * 是否已启用
	 */
	@Type(type = "yes_no")
	@Column(name = "ENABLE")
	private Boolean enable;

	/**
	 * 是否已使用完关闭，不会再启用
	 */
	@Type(type = "yes_no")
	@Column(name = "CLOSE")
	private Boolean close;
	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

}
