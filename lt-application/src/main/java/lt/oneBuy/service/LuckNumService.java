package lt.oneBuy.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.oneBuy.entity.LuckNum;
import lt.oneBuy.qo.LuckNumQO;
import gzlazypack.common.component.BaseDao;

@Service
@Transactional
public class LuckNumService extends BaseDao<LuckNum, LuckNumQO>{
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, LuckNumQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<LuckNum> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckNum.class;
	}

}
