package lt.oneBuy.service;

import lt.oneBuy.entity.LotteryResult;
import lt.oneBuy.qo.LotteryResultQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzlazypack.common.component.BaseDao;
@Service
@Transactional
public class LotteryResultService extends BaseDao<LotteryResult, LotteryResultQO>{

	@Override
	protected Criteria buildCriteria(Criteria criteria, LotteryResultQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<LotteryResult> getEntityClass() {
		// TODO Auto-generated method stub
		return LotteryResult.class;
	}

}
