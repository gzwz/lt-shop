package lt.base.entity;

import gzlazypack.common.component.BaseEvent;
import gzlazypack.common.component.BaseSaga;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import lt.base.event.SMSValidCodeCorrectEvent;
import lt.base.event.SMSValidCodePastDueEvent;
import lt.base.event.SMSValidCodeSendSuccessEvent;
import lt.base.event.SMSValidCodeTooManyTimesEvent;
import lt.base.event.SMSValidCodeWrongEvent;
import lt.base.event.SMSValidateResultUsedEvent;
import lt.base.exception.SagaException;

/**
 * 一个短信校验码流程
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "SMS_VALIDATE_SAGA")
@SuppressWarnings("serial")
public class SMSValidateSaga extends BaseSaga {
	
	/**
	 * @author wz
	 * 短信类型
	 * （1：注册验证码；2：变更密码验证码；3：车辆信息绑定验证码；
	 * 4：车辆信息解绑验证码；5：在线支付通知；6：保险过期通知；
	 * 7：运单签收）不传此参数将发送测试短信 ;8:平台预警;9:签收验证码
	 * 10：送货节点短信
	 */
	@Column(name = "TYPE")
	private short type;
	
	@Column(name = "MESSAGE")
	private String message;

	/**
	 * 验证手机号
	 */
	@Column(name = "MOBILE", length = 32)
	private String mobile;

	/**
	 * 验证码
	 */
	@Column(name = "VALID_CODE", length = 32)
	private String validCode;

	/**
	 * 当前已校验失败次数
	 */
	@Column(name = "CURRENT_VALIDATE_FAIL_TIMES", length = 4)
	private Integer currentValidateFailTimes;

	/**
	 * 最大可校验失败次数
	 */
	@Column(name = "MAX_VALIDATE_FAIL_TIMES", length = 4)
	private Integer maxValidateFailTimes;
	
	/**
	 * 最晚失效时间
	 */
	@Column(name = "FAIL_DATE", columnDefinition = M.DATE_COLUMN)
	private Date failDate;

	public static Integer DEFAULT_MAX_VALIDATE_FAIL_TIMES = 3; // 默认验证码最大校验失败次数
	public static Integer DEFAULT_VALID_TIME_MINUTE = 5; // 默认验证码有效期时长（分钟）

	public final static Integer STATUS_SMS_VALIDATE_FAIL = 0; // 验证失败
	public final static Integer STATUS_SMS_SEND = 1; // 验证码已发送
	public final static Integer STATUS_SMS_VALIDATE_SUCCESS = 2; // 验证成功

	/**
	 * 本次验证结果是否已被使用过，验证成功后只能用来操作一次
	 */
	private Boolean used;

	/**
	 * 根据发生的流程事件调用函数处理
	 */
	@Override
	public void handle(BaseEvent event) throws SagaException {

		if (getId() != null && !StringUtils.equals(event.getSagaId(), getId())) {
			throw new SagaException(SagaException.EVENT_NOT_BELONG_THIS_SAGA,
					"当前事件不属于该业务流程");
		}
		
		if (event instanceof SMSValidCodeSendSuccessEvent) {
			// 短信发送成功，创建流程
			create(((SMSValidCodeSendSuccessEvent) event).getValidCode(),
					((SMSValidCodeSendSuccessEvent) event).getMobile(),
					null,
					null);
		}

		if (event instanceof SMSValidCodeWrongEvent) {

			// 输错验证码，修改错误次数
			validateWrong();

		} else if (event instanceof SMSValidCodePastDueEvent) {

			// 超时
			pastDue();

		} else if (event instanceof SMSValidCodeTooManyTimesEvent) {

			// 次数过多
			tooManyTimes();

		} else if (event instanceof SMSValidCodeCorrectEvent) {

			// 输入正确
			success();
		} else if (event instanceof SMSValidateResultUsedEvent) {
			
			// 使用结果
			use();			
		}
	}

	/**
	 * 
	 * 创建短信验证流程
	 * 
	 * @param validCode
	 *            验证码
	 * @param mobile
	 *            验证的手机号
	 * @param maxValidateFailTimes
	 *            最大错误次数
	 * @param validTimeMinute
	 *            有效期（分钟）
	 */
	private void create(String validCode, String mobile,
			Integer maxValidateFailTimes, Integer validTimeMinute) {

		setId(UUIDGenerator.getUUID());

		if (maxValidateFailTimes != null) {
			setMaxValidateFailTimes(maxValidateFailTimes);
		} else {
			setMaxValidateFailTimes(DEFAULT_MAX_VALIDATE_FAIL_TIMES);
		}
		
		Calendar calendar = Calendar.getInstance();
		if (validTimeMinute != null) {
			calendar.add(Calendar.MINUTE, validTimeMinute);
		} else {
			calendar.add(Calendar.MINUTE, DEFAULT_VALID_TIME_MINUTE);
		}
		setFailDate(calendar.getTime());
		
		setFinish(false);
		setMobile(mobile);
		setValidCode(validCode);
		setCreateDate(new Date());

		setCurrentStatus(SMSValidateSaga.STATUS_SMS_SEND);
		setCurrentValidateFailTimes(0);
		setUsed(false);
	}

	/**
	 * 验证码超时
	 */
	private void pastDue() {
		fail();
	}

	/**
	 * 验证次数过多
	 */
	private void tooManyTimes() {
		fail();
	}

	/**
	 * 验证码错误
	 */
	private void validateWrong() {
		setCurrentValidateFailTimes(getCurrentValidateFailTimes() + 1);

		if (getCurrentValidateFailTimes() >= getMaxValidateFailTimes()) {
			fail();
		}
	}

	private void fail() {
		setFinish(true);
		setFinishDate(new Date());
		setCurrentStatus(SMSValidateSaga.STATUS_SMS_VALIDATE_FAIL);
	}

	private void success() {
		setFinish(true);
		setFinishDate(new Date());
		setSuccess(true);
		setCurrentStatus(SMSValidateSaga.STATUS_SMS_VALIDATE_SUCCESS);
	}
	
	private void use() {
		setUsed(true);
	}
	
	@Override
	public boolean checkFinish() {
		if (getCurrentStatus() == STATUS_SMS_VALIDATE_SUCCESS || getCurrentStatus() == STATUS_SMS_VALIDATE_FAIL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成随机数字验证码
	 * 
	 * @return
	 */
	public static String generateNumberRandomCode(int num) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < num; i++) {
			int x = (int) (Math.random() * 9);
			sb.append(x);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateNumberRandomCode(6));
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public Integer getMaxValidateFailTimes() {
		return maxValidateFailTimes;
	}

	public void setMaxValidateFailTimes(Integer maxValidateFailTimes) {
		this.maxValidateFailTimes = maxValidateFailTimes;
	}

	public Date getFailDate() {
		return failDate;
	}

	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}

	public Integer getCurrentValidateFailTimes() {
		return currentValidateFailTimes;
	}

	public void setCurrentValidateFailTimes(Integer currentValidateFailTimes) {
		this.currentValidateFailTimes = currentValidateFailTimes;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	


}
