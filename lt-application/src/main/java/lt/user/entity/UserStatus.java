package lt.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

/**
 * 用户状态
 * 
 * @author wxp
 * 
 */
@Embeddable
public class UserStatus {
	
	

	/**
	 * 手机验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "mobile_valid")
	private Boolean mobileValid;

	/**
	 * 邮箱验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "email_valid")
	private Boolean emailValid;

	/**
	 * 实名验证状态，包括身份证
	 */
	@Type(type = "yes_no")
	@Column(name = "real_name_valid")
	private Boolean realNameValid;
	
	/**
	 *	商家整体是否通过认证
	 */
	@Column(name = "validate_shop")
	private String validateShop;
	
	public final static String VALIDATE_COMM= "validate_comm"; // 消费者
	public final static String VALIDATE_APPLY= "validate_apply"; // 申请中
	public final static String VALIDATE_VERIFY= "validate_verify"; // 审核中
	public final static String VALIDATE_PASS= "validate_pass"; // 审核通过
	public final static String VALIDATE_UNPASS= "validate_unpass"; // 审核不通过
	
	

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login_date", columnDefinition = M.DATE_COLUMN)
	private Date lastLoginDate;

	public Boolean getMobileValid() {
		return mobileValid;
	}

	public void setMobileValid(Boolean mobileValid) {
		this.mobileValid = mobileValid;
	}

	public Boolean getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(Boolean emailValid) {
		this.emailValid = emailValid;
	}

	public Boolean getRealNameValid() {
		return realNameValid;
	}

	public void setRealNameValid(Boolean realNameValid) {
		this.realNameValid = realNameValid;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getValidateShop() {
		return validateShop;
	}

	public void setValidateShop(String validateShop) {
		this.validateShop = validateShop;
	}
	

}
