package lt.marketing.service;

import lt.marketing.entity.MarketingTarget;
import lt.marketing.qo.MarketingTargetQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MarketingTargetService extends BaseDao<MarketingTarget, MarketingTargetQO> {

	@Override
	protected Criteria buildCriteria(Criteria criteria, MarketingTargetQO qo) {
		return criteria;
	}

	@Override
	protected Class<MarketingTarget> getEntityClass() {
		return MarketingTarget.class;
	}

}
