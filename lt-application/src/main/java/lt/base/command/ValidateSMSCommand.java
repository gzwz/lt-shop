package lt.base.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 验证短信验证码
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class ValidateSMSCommand extends BaseCommand {

	/**
	 * 输入的验证码
	 */
	private String validCode;
	/**
	 * 输入的验证码对应的手机号
	 */
	private String moblie;
	/**
	 * 输入的验证码Id
	 */
//	private String validCode;

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	

}
