package lt.admin.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 创建汇银帐号
 * 
 * @author yuxiaoxiang
 * 
 */
@SuppressWarnings("serial")
public class CreateAuthAccountCommand extends BaseCommand {

	/**
	 * 帐号所属客户端类型
	 */
	private String clientType;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 明文密码
	 */
	private String password;

	/**
	 * 关联的实体id
	 */
	private String domainId;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

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

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

}
