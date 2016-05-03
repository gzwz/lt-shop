package lt.base.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.base.entity.Province;
import lt.base.qo.ProvinceQO;

@Service
@Transactional
public class ProvinceService extends BaseDao<Province, ProvinceQO> {
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, ProvinceQO qo) {
		return criteria;
	}

	@Override
	protected Class<Province> getEntityClass() {
		return Province.class;
	}

}
