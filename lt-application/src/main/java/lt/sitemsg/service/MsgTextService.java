package lt.sitemsg.service;

import gzlazypack.common.component.BaseDao;
import lt.sitemsg.command.text.CloseMsgTextCommand;
import lt.sitemsg.command.text.CreateMsgTextCommand;
import lt.sitemsg.entity.MsgText;
import lt.sitemsg.qo.MsgTextQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MsgTextService extends BaseDao<MsgText, MsgTextQO> {

	@Autowired
	private MsgTemplateService msgTemplateServiceImpl;

	public MsgText createMsgText(CreateMsgTextCommand command) {

		MsgText text = new MsgText();
		text.create(command);
		save(text);

		return text;
	}

	public void closeMsgText(CloseMsgTextCommand command) {

		MsgText text = get(command.getMsgTextId());
		text.close();
		update(text);
	}

	protected Criteria buildCriteria(Criteria criteria, MsgTextQO qo) {
		if (qo != null) {
		}
		return criteria;
	}

	@Override
	protected Class<MsgText> getEntityClass() {
		return MsgText.class;
	}

}
