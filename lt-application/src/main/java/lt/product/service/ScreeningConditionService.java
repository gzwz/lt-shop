package lt.product.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import gzlazypack.common.component.BaseDao;
import lt.product.command.CreateScreeningConditionCommand;
import lt.product.command.ModifyScreeningConditionCommand;
import lt.product.entity.ProductCategory;
import lt.product.entity.ScreeningCondition;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ScreeningConditionQO;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Transactional
public class ScreeningConditionService  extends BaseDao<ScreeningCondition, ScreeningConditionQO>{
	
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	public ScreeningCondition createScreeningCondition(CreateScreeningConditionCommand command){
		
		ScreeningCondition parent = get(command.getParentId());
		
		Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
		
		ScreeningCondition screeningCondition = new ScreeningCondition();
      
		screeningCondition.create(command, parent,productCategorys);
		  if(null==parent){ 
			  screeningCondition.setParent(screeningCondition);
			}
		save(screeningCondition);
		return screeningCondition;
	}
	
	
	
	public ScreeningCondition modifyScreeningCondition(ModifyScreeningConditionCommand command){

		Set<ProductCategory> productCategorys=queryProductCategory(command.getProductCatyIds());
		
		ScreeningCondition parent = get(command.getParentId());
		
		ScreeningCondition screeningCondition = get(command.getScreeningConditionId());
		
		screeningCondition.modify(command, parent, productCategorys);
		 if(null==parent){ 
			  screeningCondition.setParent(screeningCondition);
			}
		 update(screeningCondition);
		 return screeningCondition;
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
	  
	  public void getScreeningCondition(HttpServletRequest request, Model model) {

			ProductCategoryQO productCategoryQO1 = new ProductCategoryQO();
			productCategoryQO1.setFetchProductCategory(true);
			productCategoryQO1.setOrderBy(1);
			List<ProductCategory> pdcList = productCategoryService
					.queryList(productCategoryQO1);
			String productCategoryId = "";
			for (ProductCategory cg : pdcList) {
				if (StringUtils.equals("价格", cg.getName())) {
					productCategoryId = cg.getId();
				}
			}

			ScreeningConditionQO screeningConditionQO = new ScreeningConditionQO();
			screeningConditionQO.setOrderBy(1);
			screeningConditionQO.setProductCategoryQO(new ProductCategoryQO());
			screeningConditionQO.getProductCategoryQO().setId(productCategoryId);
			List<ScreeningCondition> scList = queryList(screeningConditionQO);

			model.addAttribute("scList", scList);
		}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ScreeningConditionQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ScreeningCondition> getEntityClass() {
		// TODO Auto-generated method stub
		return ScreeningCondition.class;
	}

}
