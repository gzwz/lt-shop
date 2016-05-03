package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 用户端登录
 * 
 * @author yuxx
 * 
 */
@SuppressWarnings("serial")
public class AdminLoginCommand extends BaseCommand {

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 登录类型
	 */
	private String type;

	public final static String TYPE_COMMON = "common"; // 普通用户
	public final static String TYPE_MERCHANT = "merchant"; // 代理商

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
