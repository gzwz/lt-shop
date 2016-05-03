package lt.marketing.service;

import lt.marketing.command.CreateMarketingEntryCommand;
import lt.marketing.command.EnterMarketingEntryCommand;
import lt.marketing.entity.MarketingEntry;
import lt.marketing.entity.MarketingSubject;
import lt.marketing.entity.MarketingTarget;
import lt.marketing.entity.MarketingToken;
import lt.marketing.qo.MarketingEntryQO;
import lt.marketing.qo.MarketingSubjectQO;
import lt.marketing.qo.MarketingTargetQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MarketingEntryService extends
		BaseDao<MarketingEntry, MarketingEntryQO> {

	@Autowired
	private MarketingSubjectService marketingSubjectService;
	@Autowired
	private MarketingTargetService marketingTargetService;
	@Autowired
	private MarketingTokenService marketingTokenService;

	public MarketingEntry createMarketingEntry(
			CreateMarketingEntryCommand command){

		// 检查是否已生成过入口，subjectId,subjectType,targetId,targetType全相同视为重复入口，即某个推广主体已经推广过同一个推广目标
		MarketingEntryQO marketingEntryQO = new MarketingEntryQO();
		marketingEntryQO.setSubjectQO(new MarketingSubjectQO());
		marketingEntryQO.setTargetQO(new MarketingTargetQO());
		marketingEntryQO.setSubjectQO(new MarketingSubjectQO());
		marketingEntryQO.getSubjectQO().setDomainId(command.getSubjectId());
		marketingEntryQO.getSubjectQO().setDomainType(command.getSubjectType());
		marketingEntryQO.setTargetQO(new MarketingTargetQO());
		marketingEntryQO.getTargetQO().setDomainId(command.getTargetId());
		marketingEntryQO.getTargetQO().setDomainType(command.getTargetType());
		MarketingEntry entry = queryUnique(marketingEntryQO);
		
		if (entry != null) {
			return entry;
		}
		
		//	未推广过，开始新建推广入口
		// 先查出推广主体
		MarketingSubject subject = marketingSubjectService.queryUnique(marketingEntryQO.getSubjectQO());
		if (subject == null) {
			return null;
		}

		// 检查推广目标是否已创建
		MarketingTargetQO targetQO = new MarketingTargetQO();
		targetQO.setDomainId(command.getTargetId());
		targetQO.setDomainType(command.getTargetType());
		MarketingTarget target = marketingTargetService.queryUnique(targetQO);

		// 推广目标如果不存在，创建一个
		if (target == null) {
			target = new MarketingTarget();
			target.create(command);
			marketingTargetService.save(target);
		}

		// 创建推广入口
		entry = new MarketingEntry();
		entry.create(command, subject, target);
		save(entry);
		return entry;
	}

	public MarketingToken enterMarketingEntry(EnterMarketingEntryCommand command) {

		MarketingEntry entry = get(command.getEntryId());
		if (entry == null) {
			return null;
		}
		MarketingToken token = new MarketingToken();
		token.create(command, entry);
		marketingTokenService.save(token);
		return token;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, MarketingEntryQO qo) {
		return criteria;
	}

	@Override
	protected Class<MarketingEntry> getEntityClass() {
		return MarketingEntry.class;
	}

}
