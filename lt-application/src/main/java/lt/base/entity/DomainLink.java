package lt.base.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 关联到业务模块的领域实体，可以是用户、商品、订单等
 * @author wxp
 *
 */
@Embeddable
public class DomainLink {

	/**
	 * 实体id
	 */
	@Column(name = "DOMAIN_ID", length = 64) 
	private String domainId;

	/**
	 * 实体名称
	 */
	@Column(name = "DOMAIN_NAME", length = 256) 
	private String domainName;

	/**
	 * 实体类型
	 */
	@Column(name = "DOMAIN_TYPE", length = 64) 
	private String domainType;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

}
