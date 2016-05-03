package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.command.ValidateSMSCommand;

/**
 * 短信验证记录
 * 
 * @author xps15
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "SMS_VALIDATE_RECORD")
@SuppressWarnings("serial")
public class SMSValidateRecord extends StringIdBaseEntity {

	/**
	 * 流程id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAGA_ID", nullable = false)
	private SMSValidateSaga saga;

	/**
	 * 手机号
	 */
	@Column(name = "MOBILE", length = 16)
	private String mobile;

	/**
	 * 输入的验证码
	 */
	@Column(name = "VALIDATE_CODE", length = 16)
	private String validateCode;

	/**
	 * 验证结果
	 */
	@Column(name = "RESULT", length = 8)
	private Integer result;

	/**
	 * 验证时间
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;
	
	public void create(ValidateSMSCommand command, String mobile, SMSValidateSaga saga) {
		setId(UUIDGenerator.getUUID());
		
		setMobile(mobile);
		setSaga(saga);
		setValidateCode(command.getValidCode());
		setCreateDate(new Date());
	}
	
	public SMSValidateSaga getSaga() {
		return saga;
	}

	public void setSaga(SMSValidateSaga saga) {
		this.saga = saga;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
