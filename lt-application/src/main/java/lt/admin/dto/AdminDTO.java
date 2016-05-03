package lt.admin.dto;

import java.util.Date;
import java.util.Set;

import lt.admin.entity.Role;
import lt.base.dto.BaseDTO;
/**
 * 
 * @author wxp
 *
 */
public class AdminDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	/**
	 * 密文密码
	 */
	private String encryptPassword;
	
	private String name;

	private String telephone;

	private Date lastLoginDate;
	
	private String type;

	private Set<Role> roles;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
