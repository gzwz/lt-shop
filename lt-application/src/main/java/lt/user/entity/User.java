package lt.user.entity;

import java.util.Date;
import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.PropertiesUtil;
import gzlazypack.common.util.PwdUtil;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




import lt.user.command.ChangeUserCommand;
import lt.user.command.CreateUserCommand;
import lt.user.command.ModifyUserCommand;
import lt.user.command.UpdateMobileCommand;
import lt.user.command.UpdatePasswordCommand;
import lt.user.command.UserRegisterCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "USER")
public class User extends StringIdBaseEntity{
	
	/**
	 * 用户基本信息
	 */
	private UserBaseInfo baseInfo;

	/**
	 * 用户联系信息
	 */
	private UserContactInfo contactInfo;
	
	/**
	 * 用户登录信息
	 */
	private UserLoginInfo userLoginInfo;

	/**
	 * 用户状态信息
	 */
	private UserStatus status;
	
	
	
	/**
	 * 用户是否可用状态
	 */
	@Column(name = "isEnable", length = 20)
	private String isEnable;

	/**
	 * 用户类型
	 */
	@Column(name = "type")
	private String type;
	
	public final static String TYPE_COMMON_USER = "common_user"; // 普通用户
	public final static String TYPE_MENBER_USER = "member_user"; // 会员用户
	public final static String TYPE_DRIVER_USER = "driver_user"; // 驾驶员用户
	public final static String TYPE_MERCHANT_USER = "merchant_user"; // 商家用户
	public final static String TYPE_OWNER_USER = "owner_user"; // 业主用户
	
	
	/**
	 * 二维码
	 */
	@Column(name = "qr_code" , length =1000)
	private String codeImage;
	
	/**
	 * 虚拟帐号id，可往帐户里充钱
	 */
	@Column(name = "VIRTUAL_ACCOUNT_ID", length = 64)
	private String virtualAccountId;
	

	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_user_id")
	private User parent;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.REMOVE })
	private Set<User> children;
	
	/**
	 * 用户是否可用状态
	 */
	public final static String TYPE_USER_ENABLE = "enable"; // 用户处于正常状态
	public final static String TYPE_USER_DISABLE = "disable"; // 用户处于不可用状态
	
	
	
	public void changeStatus(ChangeUserCommand command){
		setId(command.getUserId());
		if (command.getIsEnable().equals(TYPE_USER_ENABLE)) {
			setIsEnable(TYPE_USER_DISABLE);
		}else if (command.getIsEnable().equals(TYPE_USER_DISABLE)) {
			setIsEnable(TYPE_USER_ENABLE);
		}
		
	}
	
	public void createFromRegister(UserRegisterCommand command,User parent){
		
		setId(UUIDGenerator.getUUID());
		
		setBaseInfo(new UserBaseInfo());
		getBaseInfo().setIntegral(0);
		getBaseInfo().setNickName(command.getNickName());
		getBaseInfo().setRegisterTime(new Date());
		
		setContactInfo(new UserContactInfo());
		getContactInfo().setMobile(command.getMobile());
		
		setUserLoginInfo(new UserLoginInfo());
		getUserLoginInfo().setLoginName(command.getLoginName());
		getUserLoginInfo().setEncryptPassword(PwdUtil.getPwd(command.getEncryptPassword()));
		
		setStatus(new UserStatus());
		getStatus().setMobileValid(true);
		getStatus().setEmailValid(false);
		getStatus().setRealNameValid(false);
		
		setType(TYPE_COMMON_USER);
		setIsEnable(TYPE_USER_ENABLE);
		
		setParent(parent);
		
	}
	
	
	public void modify(ModifyUserCommand command) {
		
		getBaseInfo().setNickName(command.getNickName());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setIdCardNo(command.getIdCardNo());
		getBaseInfo().setHeadImage(command.getHeadImage());
		
		getContactInfo().setMobile(command.getMobile());
		getContactInfo().setEmail(command.getEmail());
		
		getUserLoginInfo().setLoginName(command.getLoginName());
		
	}
	
	public void createUser(CreateUserCommand command,User parent){
		
		setId(UUIDGenerator.getUUID());
		
		setUserLoginInfo(new UserLoginInfo());
		getUserLoginInfo().setLoginName(command.getLoginName());
		getUserLoginInfo().setEncryptPassword(PwdUtil.getPwd(command.getEncryptPassword()));

		setBaseInfo(new UserBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setNickName(command.getNickName());
		getBaseInfo().setIdCardNo(command.getIdCardNo());
	    getBaseInfo().setHeadImage(command.getHeadImage());
		getBaseInfo().setRegisterTime(new Date());
		getBaseInfo().setIntegral(0);
		
		setContactInfo(new UserContactInfo());
		getContactInfo().setMobile(command.getMobile());
		getContactInfo().setEmail(command.getEmail());
		
		setStatus(new UserStatus());
		getStatus().setMobileValid(true);
		getStatus().setEmailValid(true);
		getStatus().setRealNameValid(true);
		getStatus().setValidateShop(UserStatus.VALIDATE_COMM);
		
		setIsEnable(TYPE_USER_ENABLE);
		setType(TYPE_COMMON_USER);
		
		setParent(parent);
	}
	
	public void updateMobile(UpdateMobileCommand command){
		getContactInfo().setMobile(command.getNewMobile());
	}
	
	public void updatePassword(UpdatePasswordCommand command){
		getUserLoginInfo().setEncryptPassword(PwdUtil.getPwd(command.getNewPassword()));
	}
	
	public void lastLoginDate() {
		getStatus().setLastLoginDate(new Date());
	}

	
	public UserBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(UserBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public UserContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(UserContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}
	
	public UserLoginInfo getUserLoginInfo() {
		return userLoginInfo;
	}

	public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
		this.userLoginInfo = userLoginInfo;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Set<User> getChildren() {
		return children;
	}

	public void setChildren(Set<User> children) {
		this.children = children;
	}

	public String getCodeImage() {
		return codeImage;
	}

	public void setCodeImage(String codeImage) {
		this.codeImage = codeImage;
	}

	public String getVirtualAccountId() {
		return virtualAccountId;
	}

	public void setVirtualAccountId(String virtualAccountId) {
		this.virtualAccountId = virtualAccountId;
	}
	
	

}
