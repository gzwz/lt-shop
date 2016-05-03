package lt.order.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.order.command.CreateShoppingCarItemCommand;
import lt.order.command.ModifyShoppingCarItemCommand;
import lt.order.command.ModifyShoppingCarNumCommand;
import lt.order.command.ValidShoppingCarCommand;
import lt.order.entity.ShoppingCar;
import lt.order.entity.ShoppingCarItem;
import lt.order.qo.ShoppingCarItemQO;
import gzlazypack.common.component.BaseDao;

@Service
@Transactional
public class ShoppingCarItemService extends BaseDao<ShoppingCarItem, ShoppingCarItemQO>{
	

	public ShoppingCarItem createShoppingCarItem(CreateShoppingCarItemCommand command,ShoppingCar shoppingCar){
		
		ShoppingCarItem  item=new ShoppingCarItem();
		item.create(command, shoppingCar);
		save(item);
		return item;
	}
	
   public ShoppingCarItem modifyShoppingCarItem(ModifyShoppingCarItemCommand command,ShoppingCar shoppingCar){
		
		ShoppingCarItem  item=get(command.getShoppingCarItemId());
		item.modify(command, shoppingCar);
		update(item);
		return item;
	}
   
   
   public ShoppingCarItem modifyNum(ModifyShoppingCarNumCommand command){
	   ShoppingCarItemQO qo=new ShoppingCarItemQO();
	   qo.setFetchShoppingCar(true);
	   qo.setId(command.getShoppingCarId());
	   ShoppingCarItem item = queryUnique(qo);
	    item.updateNum(command);
		update(item);
		return item;
	}
   
   
   public void valid(ValidShoppingCarCommand command){
		ShoppingCarItem item = get(command.getShoppingCarId());
		item.validate();
		update(item);
	}
   
	@Override
	protected Criteria buildCriteria(Criteria criteria, ShoppingCarItemQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ShoppingCarItem> getEntityClass() {
		// TODO Auto-generated method stub
		return ShoppingCarItem.class;
	}

}
