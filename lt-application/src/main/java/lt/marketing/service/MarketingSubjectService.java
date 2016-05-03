package lt.marketing.service;

import lt.marketing.command.CreateMarketingSubjectCommand;
import lt.marketing.entity.MarketingSubject;
import lt.marketing.qo.MarketingSubjectQO;
import gzlazypack.common.component.BaseDao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MarketingSubjectService extends
		BaseDao<MarketingSubject, MarketingSubjectQO> {
	
	public MarketingSubject createMarketingSubject(CreateMarketingSubjectCommand command) {
		
		MarketingSubject parentSubject = null;
		if (StringUtils.isNotBlank(command.getParentSubjectId())) {
			parentSubject = get(command.getParentSubjectId());
		}
		
		MarketingSubject subject = new MarketingSubject();
		subject.create(command, parentSubject);
		save(subject);
		return subject;
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, MarketingSubjectQO qo) {
		return criteria;
	}

	@Override
	protected Class<MarketingSubject> getEntityClass() {
		return MarketingSubject.class;
	}

}
