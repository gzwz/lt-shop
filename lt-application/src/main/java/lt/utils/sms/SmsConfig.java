package lt.utils.sms;

/**
 * 短信账号配置信息
 * @author xiaobin
 *
 */
public class SmsConfig {
	/**
	 * 短信服务地址：如sandboxapp.cloopen.com
	 */
	private String url; 
	/**
	 * 短信服务端口
	 */
	private String port;
	/**
	 * 短信服务账号
	 */
	private String accountSid; 
	/**
	 * 短信服务授权token
	 */
	private String authToken; 
	/**
	 * 短信服务应用id
	 */
	private String appId; 
	/**
	 * 短信服务应用token
	 */
	private String appToken;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppToken() {
		return appToken;
	}
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	
	
}
