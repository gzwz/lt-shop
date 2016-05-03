package lt.base.service;

import gzlazypack.common.component.BaseDao;
import gzlazypack.common.component.BaseEvent;
import gzlazypack.common.util.DateUtil;

import java.io.IOException;
import java.util.Date;

import lt.base.command.SMSValidateResultUsedCommand;
import lt.base.command.SendSMSValidCodeCommand;
import lt.base.command.ValidateSMSCommand;
import lt.base.entity.SMSValidateRecord;
import lt.base.entity.SMSValidateSaga;
import lt.base.entity.SendResultBean;
import lt.base.event.MsgTemplate_w;
import lt.base.event.SMSValidCodeCorrectEvent;
import lt.base.event.SMSValidCodePastDueEvent;
import lt.base.event.SMSValidCodeSendSuccessEvent;
import lt.base.event.SMSValidCodeTooManyTimesEvent;
import lt.base.event.SMSValidCodeWrongEvent;
import lt.base.event.SMSValidateResultUsedEvent;
import lt.base.exception.SagaException;
import lt.base.qo.SMSValidateSagaQO;
import lt.utils.sms.SendSMSUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class SMSValidateSagaService extends
		BaseDao<SMSValidateSaga, SMSValidateSagaQO> {

	/**
	 * 发送短信
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 * 
	 * @author WZ
	 */
	public  SMSValidateSaga sendSMS(SendSMSValidCodeCommand command)
			throws SagaException {
		SMSValidateSaga saga = new SMSValidateSaga();
		
		//生成随机验证码
		String validCode = SMSValidateSaga.generateNumberRandomCode(6);
		String mobile = command.getMobile();
		short type = command.getType();
		// 调用短信发送接口
		command.setValidCode(validCode);
		command.setContent(String.format(MsgTemplate_w.GetMsgTemplateContent(type),new String[] {validCode}));
		command.setAddTime(DateUtil.getCurMilli());
		
	//	Map<String,Object> mData = new HashMap<String,Object>();
		if(!StringUtils.isNotBlank(mobile)){
			saga.setType(Short.parseShort("0"));
			saga.setMessage("手机号为空");
			return saga;
		//	mData.put("code", "1");
		//	mData.put("message", "手机号为空");
		//	return AppUtil.getMap(mData);
		}
		if("".equals(type)||type==0) {
			//type = Short.parseShort("0");
			saga.setType(Short.parseShort("0"));
		}
		// 调用发送短信的方法【使用短信平台】
		// 创建短信对象、并装入短信相关数据（手机号：必须、短信内容：必须、发送时间：可为空）
		
		// 调用发送短信的借口、并得到接口处理后返回的结果对象
		command.setSendDate("");// 验证码 立即发送
		try {
			SendResultBean sendResultBean = SendSMSUtil.sendMessage(command);
			saga.setMessage("发送成功");
			saga.setType(type);
			saga.setValidCode(validCode);
			saga.setMessage(sendResultBean.getSendResult());
		} catch (IOException e) {
			e.printStackTrace();
			saga.setMessage("发送失败");
			saga.setType(Short.parseShort("0"));
		}
		

		SMSValidCodeSendSuccessEvent event = new SMSValidCodeSendSuccessEvent();
		event.setSagaId(command.getSagaId());
		event.setScene(command.getScene());
		event.setMobile(mobile);
		event.setValidCode(validCode);
		saga.handle(event);

		save(saga);
		return saga;
	}

	/**
	 * 校验短信
	 * 
	 * @param command
	 * @throws SagaException
	 */
	@Transactional(noRollbackFor = SagaException.class)
	public void validateSMS(ValidateSMSCommand command,SMSValidateSaga saga) throws SagaException {
	//	SMSValidateSaga saga = get(command.getSagaId());

		if (saga == null) {
			throw new SagaException(SagaException.SAGA_NOT_EXISTS, "该流程不存在");
		} else if (saga.getUsed()) {
			throw new SagaException(SagaException.VALIDCODE_PAST_DUE, "验证码已使用过");
		}

		BaseEvent event = null;
		SagaException exception = null;
		// 根据判断结果产生事件
		if (saga.getFailDate().before(new Date())) {
			event = new SMSValidCodePastDueEvent("验证码过期");
			exception = new SagaException(SagaException.VALIDCODE_PAST_DUE,"验证码已过期");
		}

		if (saga.getCurrentValidateFailTimes() >= saga
				.getMaxValidateFailTimes()) {
			event = new SMSValidCodeTooManyTimesEvent("验证次数过多");
			exception = new SagaException(
					SagaException.VALIDCODE_TOO_MANY_TIMES, "验证次数过多");
		}
		if (StringUtils.equals(command.getValidCode(), saga.getValidCode())) {
			event = new SMSValidCodeCorrectEvent("验证码输入正确");
		} else {
			event = new SMSValidCodeWrongEvent("验证码输入错误");
			exception = new SagaException(SagaException.VALIDCODE_WRONG,
					"验证码错误");
		}

		event.setSagaId(command.getSagaId());

		// 处理事件，更新状态
		saga.handle(event);

		update(saga);

		SMSValidateRecord validateRecord = new SMSValidateRecord();
		validateRecord.create(command, saga.getMobile(), saga);
		save(validateRecord);

		if (exception != null) {
			throw exception;
		}
	}

	public void validateResultUsed(SMSValidateResultUsedCommand command)
			throws SagaException {
		SMSValidateSaga saga = get(command.getSagaId());

		if (saga.getUsed()) {
			throw new SagaException(SagaException.VALIDCODE_PAST_DUE, "验证码已使用过");
		} else if (saga.getCurrentStatus() != SMSValidateSaga.STATUS_SMS_VALIDATE_SUCCESS) {
			throw new SagaException(SagaException.SAGA_NOT_SUCCESS, "流程尚未成功");
		} else {
			SMSValidateResultUsedEvent event = new SMSValidateResultUsedEvent(
					saga.getId() + "验证码" + saga.getValidCode() + "已被成功使用");
			event.setSagaId(command.getSagaId());
			saga.handle(event);
			update(saga);
		}
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, SMSValidateSagaQO qo) {
		return criteria;
	}

	@Override
	protected Class<SMSValidateSaga> getEntityClass() {
		return SMSValidateSaga.class;
	}

}
