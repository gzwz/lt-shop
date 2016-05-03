package lt.product.service;

import lt.product.command.CreateProductCommand;
import lt.product.command.ModifyProductCommand;
import lt.product.entity.Product;
import lt.product.entity.ProductStatistics;
import lt.product.qo.ProductStatisticsQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductStatisticsService extends BaseDao<ProductStatistics, ProductStatisticsQO>{
	
	public ProductStatistics createProductStatistics(CreateProductCommand command,Product product){
		
		ProductStatistics productStatistics=new ProductStatistics();
		productStatistics.create(command, product);
		save(productStatistics);
		return productStatistics;
	}
	
	public ProductStatistics modifyProductStatistics(ModifyProductCommand command,Product product){
		
		ProductStatistics productStatistics=get(command.getProductStatisticsId());
		productStatistics.modify(command, product);
		update(productStatistics);
		return productStatistics;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductStatisticsQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductStatistics> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductStatistics.class;
	}

}
