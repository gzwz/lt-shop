package lt.sitemsg.service;

import gzlazypack.common.component.BaseDao;

import java.util.Date;
import java.util.List;

import lt.sitemsg.command.notice.CheckMsgNoticeCommand;
import lt.sitemsg.command.notice.OpenMsgNoticeCommand;
import lt.sitemsg.command.notice.RemoveMsgNoticeCommand;
import lt.sitemsg.command.notice.SendBatchMsgCommand;
import lt.sitemsg.command.notice.SendSingleMsgCommand;
import lt.sitemsg.command.text.CreateMsgTextCommand;
import lt.sitemsg.entity.MsgNotice;
import lt.sitemsg.entity.MsgSendPlan;
import lt.sitemsg.entity.MsgTemplate;
import lt.sitemsg.entity.MsgText;
import lt.sitemsg.exception.MsgException;
import lt.sitemsg.qo.MsgNoticeQO;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MsgNoticeService extends BaseDao<MsgNotice, MsgNoticeQO> {

	@Autowired
	private MsgTemplateService msgTemplateService;
	@Autowired
	private MsgSendPlanService msgSendPlanService;
	@Autowired
	private MsgTextService msgTextService;

	public void sendSingleMsg(SendSingleMsgCommand command) {
		MsgTemplate template = msgTemplateService.get(command.getMsgTemplateId());

		// 创建消息正文
		CreateMsgTextCommand createMsgTextCommand = new CreateMsgTextCommand();
		createMsgTextCommand.setTitle(template.getTitle());
		createMsgTextCommand.setContent(template.getContent());

		// 替换专用性文字
		if (command.getParams() != null && command.getParams().length > 0) {
			for (String param : command.getParams()) {
				createMsgTextCommand.setContent(createMsgTextCommand.getContent().replaceFirst("\\{P\\}", param));
			}
		}
		createMsgTextCommand.setPastDueDate(command.getPastDueDate());

		MsgText msgText = msgTextService.createMsgText(createMsgTextCommand);

		// 创建消息通知
		MsgNotice msgNotice = new MsgNotice();
		msgNotice.create(command, template, msgText, null);
		save(msgNotice);
	}

	public void sendBatchMsg(SendBatchMsgCommand command) throws MsgException {
		MsgSendPlan sendPlan = msgSendPlanService.get(command.getMsgSendPlanId());

		// 创建消息正文
		CreateMsgTextCommand createMsgTextCommand = new CreateMsgTextCommand();
		createMsgTextCommand.setTitle(sendPlan.getTemplate().getTitle());
		createMsgTextCommand.setContent(sendPlan.getTemplate().getContent());

		// 替换通用性文字
		String commonContent = sendPlan.getTemplate().getContent();
		if (sendPlan.getParams() != null && sendPlan.getParams().length > 0) {
			for (String param : sendPlan.getParams()) {
				commonContent = commonContent.replaceFirst("\\{C\\}", param);
			}
		}
		createMsgTextCommand.setContent(commonContent);
		
		createMsgTextCommand.setPastDueDate(sendPlan.getPastDueDate());

		MsgText msgText = msgTextService.createMsgText(createMsgTextCommand);
		
		if (sendPlan.getUserIds() == null) {
			throw new MsgException(MsgException.USER_NULL, "收件用户不能为空");
		}
		
		// 创建每个用户的通知
		for (String userId : sendPlan.getUserIds()) {
			// 创建消息通知
			MsgNotice msgNotice = new MsgNotice();
			msgNotice.create(userId, sendPlan, msgText, null);
			save(msgNotice);
		}

	}

	public List<MsgNotice> checkMsgNoice(CheckMsgNoticeCommand command) {
		MsgNoticeQO qo = new MsgNoticeQO();
		qo.setUserId(command.getUserId());
		qo.setStatus(MsgNotice.STATUS_UN_SEE);
		qo.setGePastDueDate(new Date());
		List<MsgNotice> notices = queryList(qo);
        if(notices.size()<1)
        	return notices;
		for (MsgNotice msgNotice : notices) {
			msgNotice.see();
			update(msgNotice);
		}

		return notices;
	}

	public MsgNotice openMsgNotice(OpenMsgNoticeCommand command) {
		MsgNotice msgNotice = get(command.getMsgNoticeId());
		msgNotice.open();
		update(msgNotice);
		getSession().flush();
		
		Hibernate.initialize(msgNotice.getMsgText());
		// 将正文踢出持久化状态后进行修改并返回
		getSession().evict(msgNotice);
		MsgText msgText = msgNotice.getMsgText();
		getSession().evict(msgText);

		if (command.getParams() != null) {
			for (String param : command.getParams()) {
				msgText.setContent(msgText.getContent().replaceFirst("\\{P\\}", param));
			}
		}
		msgNotice.setMsgText(msgText);
		return msgNotice;
	}

	public void removeMsgNotice(RemoveMsgNoticeCommand command) {
		MsgNotice msgNotice = get(command.getMsgNoticeId());
		msgNotice.remove();
		update(msgNotice);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, MsgNoticeQO qo) {
		if (qo != null) {
		}
		return criteria;
	}

	@Override
	protected Class<MsgNotice> getEntityClass() {
		return MsgNotice.class;
	}
}
