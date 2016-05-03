package lt.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserLoginInfo {
	
	/**
	 * 登录名
	 */
	@Column(name = "login_name")
	private String loginName;

	/**
	 * 登录密码
	 */
	@Column(name = "encrypt_password")
	private String encryptPassword;
	
	
	

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

	
}

