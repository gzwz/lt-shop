package lt.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserContactInfo {

	/**
	 * 手机
	 */
	@Column(name = "mobile")
	private String mobile;
	
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
	
}
