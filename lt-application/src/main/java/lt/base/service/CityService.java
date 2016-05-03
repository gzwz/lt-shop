package lt.base.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.base.entity.City;
import lt.base.qo.CityQO;

@Service
@Transactional
public class CityService extends BaseDao<City, CityQO> {
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, CityQO qo) {
		return criteria;
	}

	@Override
	protected Class<City> getEntityClass() {
		return City.class;
	}

}
