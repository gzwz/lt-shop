package lt.user.dto;

import java.util.Date;

import lt.base.dto.BaseDTO;



/**
 * 
 * @author wz
 *
 */
public class UserDTO extends BaseDTO {
	private static final long serialVersionUID = -8812296756220968245L;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 密文密码
	 */
	private String encryptPassword;
	/**
	 * 真实姓名
	 */
	private String name;
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 积分
	 */
	private Integer integral;
	

	/**
	 * 头像
	 */
	private String headImage;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 手机验证状态
	 */
	private Boolean mobileValid;

	/**
	 * 邮箱验证状态
	 */
	private Boolean emailValid;

	/**
	 * 实名验证状态，包括身份证
	 */
	private Boolean realNameValid;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginDate;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	
}
