package lt.product.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gzlazypack.common.component.BaseDao;
import lt.product.command.CreateProductParameterCommand;
import lt.product.command.ModifyProductParameterCommand;
import lt.product.entity.ProductCategory;
import lt.product.entity.ProductParameter;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ProductParameterQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductParameterService extends BaseDao<ProductParameter, ProductParameterQO>{
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	public ProductParameter createProductParameter(CreateProductParameterCommand command){
		
		
		Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
		
		ProductParameter productParameter=new ProductParameter();
		productParameter.create(command,productCategorys);
		save(productParameter);
		return productParameter;
	}
	
	public ProductParameter modify(ModifyProductParameterCommand command){
		
		Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
		
		ProductParameter productParameter=get(command.getProductParameterId());
		productParameter.modify(command,productCategorys);
		update(productParameter);
		return productParameter;
	}
	
	
	private Set<ProductCategory> queryProductCategory(List<String> productCatyIds) {
		ProductCategoryQO qo = new ProductCategoryQO();
		qo.setIds(productCatyIds);
		List<ProductCategory> productCategoryList = productCategoryService.queryList(qo);
		
		Set<ProductCategory> productCategorys = new HashSet<ProductCategory>();
		
		if (productCategoryList == null) {
			return productCategorys;
		}
		
		for (ProductCategory productCategory : productCategoryList) {
			productCategorys.add(productCategory);
		}
		return productCategorys;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductParameterQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductParameter> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductParameter.class;
	}

}
