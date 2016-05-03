package lt.user.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "userService")
@SuppressWarnings("serial")
public class UserQO extends BaseQO<String>{

	@QueryCondition(name = "contactInfo.mobile", ifTrueUseLike = "mobileLike")
	private String mobile;
	
	@QueryCondition(name = "contactInfo.email", ifTrueUseLike = "mobileLike")
	private String email;
	
	@QueryCondition(name = "baseInfo.name", ifTrueUseLike = "nameLike")
	private String name;
	
	@QueryCondition(name = "baseInfo.idCardNo", ifTrueUseLike = "nameLike")
	private String idCardNo;


	@QueryCondition(name = "userLoginInfo.loginName")
	private String loginName;
	
	/**
	 * 密文密码
	 */
	@QueryCondition(name = "encryptPassword")
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	
}
