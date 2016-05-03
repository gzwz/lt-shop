package lt.admin.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lt.admin.command.CreateAdminCommand;
import lt.admin.command.ModifyAdminCommand;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ADMIN")
@SuppressWarnings("serial")
public class Admin extends StringIdBaseEntity {

	@Column(name = "NAME", length = 128)
	private String name;

	@Column(name = "TELEPHONE", length = 32)
	private String telephone;

	@Column(name = "LAST_LOGIN_DATE", columnDefinition = M.DATE_COLUMN)
	private Date lastLoginDate;

	@Column(name = "LAST_LOGIN_IP", length = 32)
	private String lastLoginIP;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<Role> roles;

	@Column(name = "AUTH_ACCOUNT_ID", length = 64)
	private String authAccountId;

	@Column(name = "TYPE", length = 32)
	private String type;

	public final static String TYPE_PLATFORM_ADMIN = "platform";
	public final static String TYPE_OPERATION_MERCHANT_ADMIN = "operation_merchant";

	public void create(CreateAdminCommand command, Set<Role> roles) {
		setId(UUIDGenerator.getUUID());

		setName(command.getName());
		setTelephone(command.getTelephone());
		setType(command.getType());
		
		if (StringUtils.isBlank(getType())) {
			setType(TYPE_PLATFORM_ADMIN);
		}
		
		setRoles(roles);
	}

	public void modify(ModifyAdminCommand command, Set<Role> roles) {
		setName(command.getName());
		setTelephone(command.getTelephone());

		setRoles(roles);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAuthAccountId() {
		return authAccountId;
	}

	public void setAuthAccountId(String authAccountId) {
		this.authAccountId = authAccountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
