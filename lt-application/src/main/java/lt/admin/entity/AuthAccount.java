package lt.admin.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.PwdUtil;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lt.admin.command.CreateAuthAccountCommand;
import lt.admin.command.ModifyAuthAccountCommand;
import lt.base.entity.DomainLink;

/**
 * 登录帐户
 * 
 * @author wxp
 * 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "AUTH_ACCOUNT")
@SuppressWarnings("serial")
public class AuthAccount extends StringIdBaseEntity {

	/**
	 * 帐号所属客户端类型
	 */
	@Column(name = "CLIENT_TYPE", length = 32)
	private String clientType;

	public final static String CLIENT_TYPE_ADMIN = "Admin"; // 平台管理后台
	public final static String CLIENT_TYPE_CLIENT = "Client"; // 平台客户端，含PC APP
	public final static String CLIENT_TYPE_MERCHANT = "Merchant"; // 平台商户端

	/**
	 * 登录名
	 */
	@Column(name = "LOGIN_NAME", length = 32)
	private String loginName;

	/**
	 * 密文密码
	 */
	@Column(name = "ENCRYPT_PASSWORD", length = 32)
	private String encryptPassword;

	/**
	 * 创建日期
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	/**
	 * 所属业务实体id
	 */
	private DomainLink domainLink;

	public void create(CreateAuthAccountCommand command) {
		setId(UUIDGenerator.getUUID());

		setLoginName(command.getLoginName());
		setEncryptPassword(PwdUtil.getPwd(command.getPassword()));
		setClientType(command.getClientType());
		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(command.getDomainId());
		getDomainLink().setDomainType(command.getClientType());
		
		setCreateDate(new Date());
	}

	public void modify(ModifyAuthAccountCommand command) {

		setLoginName(command.getLoginName());
		setEncryptPassword(PwdUtil.getPwd(command.getPassword()));
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

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getClientType() {
		return clientType;
	}

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}

}
