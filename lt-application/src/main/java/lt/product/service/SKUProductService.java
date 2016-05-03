package lt.product.service;

import gzlazypack.common.component.BaseDao;
import lt.product.command.CreateProductCommand;
import lt.product.command.ModifySKUProductCommand;
import lt.product.entity.Product;
import lt.product.entity.SKUProduct;
import lt.product.qo.SKUProductQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SKUProductService extends BaseDao<SKUProduct, SKUProductQO>{

	public SKUProduct createSKUProduct(CreateProductCommand command,Product product){
		SKUProduct sKUProduct=new SKUProduct();
		sKUProduct.create(command, product);
		save(sKUProduct);
		return sKUProduct;
	}
	
	public SKUProduct modifySKUProduct(ModifySKUProductCommand command){
		
		SKUProduct sKUProduct=get(command.getsKUProductId());
		sKUProduct.modify(command);
		update(sKUProduct);
		return sKUProduct;
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, SKUProductQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<SKUProduct> getEntityClass() {
		// TODO Auto-generated method stub
		return SKUProduct.class;
	}

}
