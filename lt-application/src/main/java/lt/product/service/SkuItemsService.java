package lt.product.service;

import gzlazypack.common.component.BaseDao;
import lt.product.entity.Product;
import lt.product.entity.ProductParameter;
import lt.product.entity.SkuItems;
import lt.product.qo.SkuItemsQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkuItemsService extends BaseDao<SkuItems, SkuItemsQO>{
	
	public SkuItems createSkuItems(String skums,Product product,ProductParameter parameter){
		
		SkuItems skuItems=new SkuItems();
		skuItems.create(skums, product, parameter);
		save(skuItems);
		return skuItems;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, SkuItemsQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<SkuItems> getEntityClass() {
		// TODO Auto-generated method stub
		return SkuItems.class;
	}

}
