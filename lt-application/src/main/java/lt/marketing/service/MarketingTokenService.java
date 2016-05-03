package lt.marketing.service;

import lt.marketing.entity.MarketingToken;
import lt.marketing.qo.MarketingTokenQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MarketingTokenService extends BaseDao<MarketingToken, MarketingTokenQO>{

	@Override
	protected Criteria buildCriteria(Criteria criteria, MarketingTokenQO qo) {
		return criteria;
	}

	@Override
	protected Class<MarketingToken> getEntityClass() {
		return MarketingToken.class;
	}
	
}
