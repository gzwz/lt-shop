package lt.product.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import lt.product.command.CreateProductBrandCommand;
import lt.product.command.ModifyProductBrandCommand;
import lt.product.entity.ProductBrand;
import lt.product.entity.ProductCategory;
import lt.product.qo.ProductBrandQO;
import lt.product.qo.ProductCategoryQO;
import gzlazypack.common.component.BaseDao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Transactional
public class ProductBrandService extends BaseDao<ProductBrand, ProductBrandQO>{
	
	@Autowired
	private ProductCategoryService productCategoryService;
	

	public ProductBrand createProductBrand(CreateProductBrandCommand command){
		
		Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
		
		ProductBrand parent=get(command.getParentId());
		
		ProductBrand productBrand=new ProductBrand();
		productBrand.create(command,parent,productCategorys);
		if(null==parent){
			productBrand.setParent(productBrand);
		}
		save(productBrand);
		return productBrand;
	}
	
  public ProductBrand modifyProductBrand(ModifyProductBrandCommand command){
	 
	    Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
	  
	    ProductBrand parent=get(command.getParentId());
	    
		ProductBrand productBrand=get(command.getProductBrandId());
		productBrand.modify(command,parent,productCategorys);
		if(null==parent){
			productBrand.setParent(productBrand);
		}
		update(productBrand);
		return productBrand;
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
  
  
  public void getProductBrand(HttpServletRequest request, Model model) {

		ProductCategoryQO productCategoryQO1 = new ProductCategoryQO();
		productCategoryQO1.setFetchProductCategory(true);
		productCategoryQO1.setOrderBy(1);
		List<ProductCategory> pdcList = productCategoryService
				.queryList(productCategoryQO1);
		String productCategoryId = "";
		for (ProductCategory cg : pdcList) {
			if (StringUtils.equals("品牌", cg.getName())) {
				productCategoryId = cg.getId();
			}
		}

		ProductBrandQO brandQO = new ProductBrandQO();
		brandQO.setOrderBy(1);
		brandQO.setProductCategoryQO(new ProductCategoryQO());
		brandQO.getProductCategoryQO().setId(productCategoryId);
		List<ProductBrand> brandList =queryList(brandQO);

		model.addAttribute("brandList", brandList);
	}

	
	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductBrandQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductBrand> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductBrand.class;
	}

}
