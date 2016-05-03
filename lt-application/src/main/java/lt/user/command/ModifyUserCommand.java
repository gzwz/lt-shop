package lt.user.command;

import java.util.Date;


public class ModifyUserCommand {
	

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 真实姓名
	 */
	private String name;

	/**
	 * 身份证号
	 */
	private String idCardNo;

	/**
	 * 头像
	 */
	private String headImage;

	/**
	 * 积分
	 */
	private Integer integral;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String encryptPassword;
	
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
	
	/**
	 * 用户类型
	 */
	private String type;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}


	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
