package lt.order.service;

import lt.order.command.CreateOrderConsolidationCommand;
import lt.order.entity.OrderConsolidation;
import lt.order.qo.OrderConsolidationQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderConsolidationService extends BaseDao<OrderConsolidation, OrderConsolidationQO>{
	
	public OrderConsolidation createMethed(CreateOrderConsolidationCommand command){
		
		OrderConsolidation cs=new OrderConsolidation();
		cs.create(command);
		return cs;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, OrderConsolidationQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<OrderConsolidation> getEntityClass() {
		// TODO Auto-generated method stub
		return OrderConsolidation.class;
	}

}
