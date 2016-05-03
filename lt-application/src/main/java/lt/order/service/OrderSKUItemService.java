package lt.order.service;

import lt.order.entity.OrderSKUItem;
import lt.order.qo.OrderSKUItemsQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderSKUItemService extends BaseDao<OrderSKUItem, OrderSKUItemsQO>{

	@Override
	protected Criteria buildCriteria(Criteria criteria, OrderSKUItemsQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<OrderSKUItem> getEntityClass() {
		// TODO Auto-generated method stub
		return OrderSKUItem.class;
	}

}
