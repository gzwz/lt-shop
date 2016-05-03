package lt.pay.alipay.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzlazypack.common.component.BaseDao;
import lt.pay.alipay.entity.NotifyData;
import lt.pay.alipay.qo.NotifyDataQO;

@Service
@Transactional
public class NotifyService extends BaseDao<NotifyData, NotifyDataQO>{
	
	
	
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, NotifyDataQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<NotifyData> getEntityClass() {
		// TODO Auto-generated method stub
		return NotifyData.class;
	}

}
