package lt.product.service;

import gzlazypack.common.component.BaseDao;
import lt.product.command.CreateProductParameterValueCommand;
import lt.product.command.ModifyProductParameterValueCommand;
import lt.product.entity.ProductParameter;
import lt.product.entity.ProductParameterValue;
import lt.product.qo.ProductParameterValueQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductParameterValueService extends BaseDao<ProductParameterValue, ProductParameterValueQO>{
	
	@Autowired
	private ProductParameterService productParameterService;
	
	@Autowired
	private ProductService productService;
	
	public ProductParameterValue createProductParameterValue(CreateProductParameterValueCommand command){
		
		ProductParameter parameter=productParameterService.get(command.getProductParameterId());
		
		ProductParameterValue productParameterValue=new ProductParameterValue();
		productParameterValue.create(command, parameter);
		save(productParameterValue);
		return productParameterValue;
	}
	
	
	public ProductParameterValue modfiyProductParameterValue(ModifyProductParameterValueCommand command){
		
        ProductParameter parameter=productParameterService.get(command.getProductParameterId());
		
		ProductParameterValue productParameterValue=get(command.getProductParameterValueId());
		productParameterValue.modify(command, parameter);
		update(productParameterValue);
		return productParameterValue;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria,
			ProductParameterValueQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductParameterValue> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductParameterValue.class;
	}

}
