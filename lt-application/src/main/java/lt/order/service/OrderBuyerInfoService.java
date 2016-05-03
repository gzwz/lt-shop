package lt.order.service;


import gzlazypack.common.component.BaseDao;
import lt.order.entity.Order;
import lt.order.entity.OrderBuyerInfo;
import lt.order.qo.OrderBuyerInfoQO;
import lt.user.entity.User;
import lt.user.entity.UserAddress;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderBuyerInfoService extends BaseDao<OrderBuyerInfo, OrderBuyerInfoQO> {
	

	public OrderBuyerInfo createOrderBuyerInfo(User buyerUser,UserAddress address,Order order){
		
		OrderBuyerInfo orderBuyerInfo=new OrderBuyerInfo();
		orderBuyerInfo.create(buyerUser, address, order);
		save(orderBuyerInfo);
		return orderBuyerInfo;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, OrderBuyerInfoQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<OrderBuyerInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return OrderBuyerInfo.class;
	}


	
	
	
 

}
