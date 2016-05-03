package lt.virtualAccount.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

/**
 * 虚拟帐户基本信息
 * 
 * 
 */
@Embeddable
public class VirtualAccountBaseInfo {

	/**
	 * 帐号名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	/**
	 * 失效时间
	 */
	@Column(name = "INVALID_DATE")
	private Date invalidDate;

	/**
	 * 是否是代理帐户
	 */
	@Type(type = "yes_no")
	@Column(name = "PROXY_ACCOUNT")
	private Boolean proxyAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Boolean getProxyAccount() {
		return proxyAccount;
	}

	public void setProxyAccount(Boolean proxyAccount) {
		this.proxyAccount = proxyAccount;
	}

}
