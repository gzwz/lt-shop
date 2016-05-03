package lt.base.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.base.entity.Area;
import lt.base.qo.AreaQO;

@Service
@Transactional
public class AreaService extends BaseDao<Area, AreaQO> {
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, AreaQO qo) {
		return criteria;
	}

	@Override
	protected Class<Area> getEntityClass() {
		return Area.class;
	}

}
