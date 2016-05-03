package lt.admin.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

import java.util.Date;

/**
 * 查询帐号
 * 
 * @author yuxiaoxiang
 * 
 */
@QueryConfig(daoBeanId = "hyAccountDao")
@SuppressWarnings("serial")
public class AuthAccountQO extends BaseQO<String> {

	/**
	 * 登录名
	 */
	@QueryCondition(name = "loginName", ifTrueUseLike = "loginNameLike")
	private String loginName;

	/**
	 * 模糊匹配登录名
	 */
	private Boolean loginNameLike = false;

	/**
	 * 密文密码
	 */
	@QueryCondition(name = "encryptPassword")
	private String encryptPassword;
	
	@QueryCondition(name = "clientType")
	private String clientType;

	@QueryCondition(name = "domainLink.domainId")
	private String domainId;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	/**
	 * 根据创建时间排序
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.ORDER)
	private int orderByDate;

	/**
	 * 创建时间起始
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.LE)
	private Date leCreateDate;

	/**
	 * 创建时间结束
	 */
	@QueryCondition(name = "createDate", type = QueryConditionType.GE)
	private Date geCreateDate;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Boolean getLoginNameLike() {
		return loginNameLike;
	}

	public void setLoginNameLike(Boolean loginNameLike) {
		this.loginNameLike = loginNameLike;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public Date getLeCreateDate() {
		return leCreateDate;
	}

	public void setLeCreateDate(Date leCreateDate) {
		this.leCreateDate = leCreateDate;
	}

	public Date getGeCreateDate() {
		return geCreateDate;
	}

	public void setGeCreateDate(Date geCreateDate) {
		this.geCreateDate = geCreateDate;
	}

	public int getOrderByDate() {
		return orderByDate;
	}

	public void setOrderByDate(int orderByDate) {
		this.orderByDate = orderByDate;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

}
