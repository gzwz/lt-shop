package lt.user.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class UserRegisterCommand extends BaseCommand{
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String encryptPassword;
	
	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 短信校验码 
	 */
	private String smsValidCode;

	/**
	 * 图片校验码
	 */
	private String imageValidCode;
	
	private String marketingTokenId;

	public String getSmsValidCode() {
		return smsValidCode;
	}

	public void setSmsValidCode(String smsValidCode) {
		this.smsValidCode = smsValidCode;
	}

	public String getImageValidCode() {
		return imageValidCode;
	}

	public void setImageValidCode(String imageValidCode) {
		this.imageValidCode = imageValidCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMarketingTokenId() {
		return marketingTokenId;
	}

	public void setMarketingTokenId(String marketingTokenId) {
		this.marketingTokenId = marketingTokenId;
	}

}
