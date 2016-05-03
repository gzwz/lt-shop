package lt.sitemsg.service;

import gzlazypack.common.component.BaseDao;
import lt.sitemsg.command.sendplan.CancelMsgSendPlanCommand;
import lt.sitemsg.command.sendplan.CreateMsgSendPlanCommand;
import lt.sitemsg.command.sendplan.ModifyMsgSendPlanCommand;
import lt.sitemsg.entity.MsgSendPlan;
import lt.sitemsg.entity.MsgTemplate;
import lt.sitemsg.qo.MsgSendPlanQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MsgSendPlanService extends BaseDao<MsgSendPlan, MsgSendPlanQO> {

	@Autowired
	private MsgTemplateService msgTemplateServiceImpl;

	public MsgSendPlan createMsgSendPlan(CreateMsgSendPlanCommand command) {
		MsgTemplate template = msgTemplateServiceImpl.get(command.getMsgTemplateId());

		MsgSendPlan plan = new MsgSendPlan();
		plan.create(command, template);
		save(plan);

		return plan;
	}

	public MsgSendPlan modifyMsgSendPlan(ModifyMsgSendPlanCommand command) {
		MsgTemplate template = msgTemplateServiceImpl.get(command.getMsgTemplateId());

		MsgSendPlan plan = get(command.getMsgSendPlanId());
		plan.modify(command, template);
		update(plan);

		return plan;
	}

	public void cancelMsgSendPlan(CancelMsgSendPlanCommand command) {
		deleteById(command.getMsgSendPlanId());
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, MsgSendPlanQO qo) {
		if (qo != null) {
		}
		return criteria;
	}

	@Override
	protected Class<MsgSendPlan> getEntityClass() {
		return MsgSendPlan.class;
	}

}
