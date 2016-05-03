package lt.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.product.command.CreateProductCategoryCommand;
import lt.product.command.ModifyProductCategoryCommand;
import lt.product.dto.ProductCategoryChildrenDTO;
import lt.product.dto.ProductCategoryDTO;
import lt.product.dto.ProductCategoryParentDTO;
import lt.product.entity.ProductCategory;
import lt.product.qo.ProductCategoryQO;
import gzlazypack.common.component.BaseDao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Transactional
public class ProductCategoryService extends
		BaseDao<ProductCategory, ProductCategoryQO> {

	public ProductCategory createProductCategory(
			CreateProductCategoryCommand command) {

		ProductCategory parent = get(command.getParentId());
		
		ProductCategory productCategory = new ProductCategory();
      
		productCategory.create(command, parent);
		  if(null==parent){ 
			  productCategory.setParent(productCategory);
			}
		save(productCategory);
		return productCategory;
	}

	public ProductCategory modifyProductCategory(
			ModifyProductCategoryCommand command) {

		ProductCategory parent = get(command.getParentId());
		
		ProductCategory productCategory = get(command.getProductCategoryId());
		productCategory.modify(command, parent);
		 if(null==parent){ 
			  productCategory.setParent(productCategory);
			}
		update(productCategory);
		return productCategory;
	}
	
	
	public void getProductCatory(HttpServletRequest request, Model model) {

		ProductCategoryQO productCategoryQO = new ProductCategoryQO();
		productCategoryQO.setFetchProductCategory(true);
		productCategoryQO.setOrderBy(1);
		List<ProductCategory> pdcList = queryList(productCategoryQO);
		List<ProductCategoryDTO> productCategoryDTO = new ArrayList<ProductCategoryDTO>();
		for (ProductCategory pd : pdcList) {

			if (StringUtils.equals(pd.getId(), pd.getParent().getId())) {
				ProductCategoryDTO pcator = new ProductCategoryDTO();
				List<ProductCategoryParentDTO> parentDTO = new ArrayList<ProductCategoryParentDTO>();

				ProductCategoryQO parentQO = new ProductCategoryQO();
				parentQO.setOrderBy(1);
				parentQO.setParendQO(new ProductCategoryQO());
				parentQO.getParendQO().setId(pd.getId());
				List<ProductCategory> parentList = queryList(parentQO);
				for (ProductCategory pdct : parentList) {
					if (!StringUtils.equals(pdct.getName(), pdct.getParent()
							.getName())) {
						List<ProductCategoryChildrenDTO> childrenDTO = new ArrayList<ProductCategoryChildrenDTO>();
						ProductCategoryParentDTO pDTO = new ProductCategoryParentDTO();
						pDTO.setId(pdct.getId());
						pDTO.setName(pdct.getName());

						ProductCategoryQO parentQO2 = new ProductCategoryQO();
						parentQO2.setOrderBy(1);
						parentQO2.setParendQO(new ProductCategoryQO());
						parentQO2.getParendQO().setId(pdct.getId());
						List<ProductCategory> parent12 =queryList(parentQO2);
						for (ProductCategory pct : parent12) {
							ProductCategoryChildrenDTO chilDTO = new ProductCategoryChildrenDTO();
							chilDTO.setId(pct.getId());
							chilDTO.setName(pct.getName());
							childrenDTO.add(chilDTO);
						}
						pDTO.setChidrenList(childrenDTO);
						parentDTO.add(pDTO);
					}
				}
				pcator.setId(pd.getId());
				pcator.setName(pd.getName());
				pcator.setParentList(parentDTO);
				productCategoryDTO.add(pcator);
			}

		}
		model.addAttribute("productCatoryList", productCategoryDTO);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductCategoryQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ProductCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return ProductCategory.class;
	}

}
