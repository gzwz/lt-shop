package lt.sitemsg.service;

import gzlazypack.common.component.BaseDao;
import lt.sitemsg.command.template.CreateMsgTemplateCommand;
import lt.sitemsg.command.template.DeleteMsgTemplateCommand;
import lt.sitemsg.command.template.DisableMsgTemplateCommand;
import lt.sitemsg.command.template.EnableMsgTemplateCommand;
import lt.sitemsg.command.template.ModifyMsgTemplateCommand;
import lt.sitemsg.entity.MsgTemplate;
import lt.sitemsg.qo.MsgTemplateQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MsgTemplateService extends BaseDao<MsgTemplate, MsgTemplateQO> {

	public MsgTemplate createMsgTemplate(CreateMsgTemplateCommand command) {
		MsgTemplate template = new MsgTemplate();
		template.create(command);
		save(template);
		return template;
	}

	public void deleteMsgTemplate(DeleteMsgTemplateCommand command) {
		deleteById(command.getMsgTemplateId());
	}

	public void disableMsgTemplate(DisableMsgTemplateCommand command) {
		MsgTemplate template = get(command.getMsgTemplateId());
		template.disable();
		update(template);
	}

	public void enableMsgTemplate(EnableMsgTemplateCommand command) {
		MsgTemplate template = get(command.getMsgTemplateId());
		template.enable();
		update(template);
	}

	public MsgTemplate modifyMsgTemplate(ModifyMsgTemplateCommand command) {
		MsgTemplate template = get(command.getMsgTemplateId());
		template.modify(command);
		update(template);
		return template;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, MsgTemplateQO qo) {
		if (qo != null) {
		}
		return criteria;
	}

	@Override
	protected Class<MsgTemplate> getEntityClass() {
		return MsgTemplate.class;
	}

}
