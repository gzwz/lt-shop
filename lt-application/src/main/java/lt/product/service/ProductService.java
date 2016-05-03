package lt.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import lt.base.command.CreateImageCommand;
import lt.base.entity.Image;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.content.entity.NavigationItem;
import lt.content.qo.NavigationItemQO;
import lt.content.service.NavigationItemService;
import lt.merchant.entity.Merchant;
import lt.merchant.service.MerchantService;
import lt.product.command.CreateProductCommand;
import lt.product.command.DisableProductCommand;
import lt.product.command.EnableProductCommand;
import lt.product.command.ModifyProductCommand;
import lt.product.dto.ProductBrandDTO;
import lt.product.dto.ScreeningConditionDTO;
import lt.product.dto.ScreeningConditionParentDTO;
import lt.product.entity.Product;
import lt.product.entity.ProductBrand;
import lt.product.entity.ProductCategory;
import lt.product.entity.ProductParameter;
import lt.product.entity.ProductParameterValue;
import lt.product.entity.ProductSnapshot;
import lt.product.entity.ScreeningCondition;
import lt.product.qo.ProductBrandQO;
import lt.product.qo.ProductCategoryQO;
import lt.product.qo.ProductParameterQO;
import lt.product.qo.ProductQO;
import lt.product.qo.ScreeningConditionQO;
import lt.utils.PageUtils;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.page.Pagination;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;

@Service
@Transactional
public class ProductService extends BaseDao<Product, ProductQO> {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductBrandService productBrandService;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProductParameterService productParameterService;
	
	/**
	 * service注解
	 */
	@Autowired
	private NavigationItemService navigationItemService;

	@Autowired
	private ProductParameterValueService productParameterValueService;

	@Autowired
	private SkuItemsService skuItemsService;

	@Autowired
	private SKUProductService sKUProductService;

	@Autowired
	private ProductStatisticsService productStatisticsService;

	@Autowired
	private ProductSnapshotService productSnapshotService;

	@Autowired
	private ScreeningConditionService screeningConditionService;
	

	public Product createProduct(CreateProductCommand command, String[] prices,
			String[] numbers, String[] skuSpecInfos, String[] merchantIds) {

		ProductCategory productCategory = productCategoryService.get(command
				.getProductCategoryId());

		Preconditions.checkArgument(productCategory != null, "【产品类目数为空】");

		ProductBrand productBrand = productBrandService.get(command
				.getProductBrandId());

		Preconditions.checkArgument(productBrand != null, "【品牌数为空】");

		// 筛选条件
		Set<ScreeningCondition> screeningConditions = queryScreeningCondition(command
				.getScreeningIds());

		Merchant merchant = merchantService.get(command.getMerchantId());

		Product product = new Product();
		product.create(command, merchant, productCategory, productBrand,
				screeningConditions);

		save(product);
       
		if(StringUtils.isNotBlank(command.getImages())){
			// 图片处理
			Map<String, String> specImageMap = new HashMap<String, String>();
			if (StringUtils.isNoneBlank(command.getImages())) {
				for (int i = 0; i < command.getImages().split(",").length; i++) {
					specImageMap.put("product_image" + i, command.getImages()
							.split(",")[i]);
				}

				CreateImageCommand imgCommand = new CreateImageCommand();
				imgCommand.setSpecImageMap(specImageMap);
				List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
				createImageCommandList.add(imgCommand);
				command.setCreateImageCommandList(createImageCommandList);
				// 创建商品图片
				if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
					int index = 0;
					for (CreateImageCommand createImageCommand : command
							.getCreateImageCommandList()) {
						createImageCommand.setDomainLinkId(product.getId());
						createImageCommand.setDomainLinkName(product.getShowInfo()
								.getName());
						createImageCommand.setDomainLinkType("product_image");
						createImageCommand.setSort(index);
						imageService.createImage(createImageCommand);
						index++;
					}
				}
			}
		}
		
		// sku
		for (int i = 0; i < prices.length; i++) {
			command.setPrice(Double.parseDouble(prices[i]));
			command.setNumber(Integer.parseInt(numbers[i]));
			command.setSkuSpecInfo(skuSpecInfos[i]);
			sKUProductService.createSKUProduct(command, product);
		}

		// skuItems

		for (int i = 0; i < command.getParameterId().split(",").length; i++) {
			ProductParameterQO qo = new ProductParameterQO();
			qo.setBatchResult(true);
			qo.setFetchProductParameterValue(true);
			if (StringUtils.isNotBlank(command.getParameterId().split(",")[i])) {
				qo.setId(command.getParameterId().split(",")[i]);
			}
			String skums = "";
			ProductParameter productParameter = productParameterService
					.queryUnique(qo);
			if (null != productParameter) {
				for (ProductParameterValue productParameterValue : productParameter
						.getProductParameterValues()) {

					for (int j = 0; j < command.getProportyValueId().split(",").length; j++) {

						if (StringUtils.equals(
								productParameterValue.getValue(), command
										.getProportyValueId().split(",")[j])) {

							skums += productParameterValue.getValue() + ",";
						}

					}

				}
				// 去掉最后一个逗号
				skums = skums.substring(0, skums.length() - 1);
				skuItemsService
						.createSkuItems(skums, product, productParameter);
			}
		}

		// 添加详情
		productStatisticsService.createProductStatistics(command, product);

		return product;
	}

	public Product modifyProduct(ModifyProductCommand command) {

		Product product = get(command.getProductId());
		product.modify(command);

		productStatisticsService.modifyProductStatistics(command, product);

		ImageQO imgeQO = new ImageQO();
		imgeQO.setDomainId(product.getId());
		Image image=imageService.queryUnique(imgeQO);
		if(null!=image){
			imageService.delete(image);
		}
		

		// 图片处理
		Map<String, String> specImageMap = new HashMap<String, String>();

		if(StringUtils.isNoneBlank(command.getImages())){
		
		for (int i = 0; i < command.getImages().split(",").length; i++) {
			specImageMap.put("product_image" + i, command.getImages()
					.split(",")[i]);
		}
		CreateImageCommand imgCommand = new CreateImageCommand();
		imgCommand.setSpecImageMap(specImageMap);
		List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
		createImageCommandList.add(imgCommand);
		command.setCreateImageCommandList(createImageCommandList);
		// 创建商品图片
		if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
			int index = 0;
			for (CreateImageCommand createImageCommand : command
					.getCreateImageCommandList()) {
				createImageCommand.setDomainLinkId(product.getId());
				createImageCommand.setDomainLinkName(product.getShowInfo()
						.getName());
				createImageCommand.setDomainLinkType("product_image");
				createImageCommand.setSort(index);
				imageService.createImage(createImageCommand);
				index++;
			}
		 }
		}
		update(product);
		return product;
	}

	public Set<ScreeningCondition> queryScreeningCondition(
			List<String> screeningIds) {

		ScreeningConditionQO qo = new ScreeningConditionQO();
		qo.setIds(screeningIds);
		List<ScreeningCondition> scd = screeningConditionService.queryList(qo);

		Set<ScreeningCondition> screeningConditions = new HashSet<ScreeningCondition>();

		if (scd == null) {
			return screeningConditions;
		}

		for (ScreeningCondition screeningCondition : scd) {
			screeningConditions.add(screeningCondition);
		}
		return screeningConditions;
	}

	public void enable(EnableProductCommand command) {
		Product product = get(command.getProductId());
		product.enable();
		update(product);

		ProductSnapshot snapshot = new ProductSnapshot();
		snapshot.create(product);
		productSnapshotService.save(snapshot);
	}

	public void disable(DisableProductCommand command) {
		Product product = get(command.getProductId());
		product.disable();
		update(product);
	}

	public void hotT(EnableProductCommand command) {
		Product product = get(command.getProductId());
		product.t();
		update(product);
	}
	

	public void hotF(DisableProductCommand command) {
		Product product = get(command.getProductId());
		product.f();
		update(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getProductId(ProductQO productQO){
		
		List<String> ids=new ArrayList<String>();
		
		if(StringUtils.isNotBlank(productQO.getScreeningConditionId())){
			StringBuffer st=new StringBuffer("select product_id from prd_product_screeningcondition where ");
			int count=0;
			for(int i=0;i<productQO.getScreeningConditionId().split(",").length;i++){
				ids.add(productQO.getScreeningConditionId().split(",")[i]);
				if(i==0){
					st.append("screeningCondition_id='"+productQO.getScreeningConditionId().split(",")[i]+"'");
				}else{
					st.append("or screeningCondition_id='"+productQO.getScreeningConditionId().split(",")[i]+"'");
				}
				count++;
			} 
			st.append("group by product_id having count(*)>="+count);
			int b=1;
			if(StringUtils.isNotBlank(productQO.getPage())){
				b=Integer.parseInt(productQO.getPage());
			}
			
			ids = (List<String>) exesql(st.toString(), b, 20);
			
		}
		
		
		return ids;
	}
	
	
	//执行sql
	public List<?> exesql(String sql, int offset, int size) {
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult(offset);
		query.setMaxResults(size);
		return query.list();
	}
	
		
		
		
		@SuppressWarnings("unchecked")
		public Map<String, String> getimage(String productExteriorId) {

			ImageQO imageQO = new ImageQO();
			imageQO.setDomainId(productExteriorId);
			imageQO.setDomainType("product_image");
			List<Image> imageExt = imageService.queryList(imageQO);
			Map<String, String> ext = (Map<String, String>) JSON.parse(imageExt
					.get(0).getSpecImageMapJSON());
			return ext;
		}

		public void getTruck(HttpServletRequest request, Model model,NavigationItemQO qo) {
			// 获取分类
			ProductCategory productCategory = getProductCategory(model, qo);

			List<ScreeningCondition> scList = null;
			if (null != productCategory) {
				ScreeningConditionQO screeningConditionQO = new ScreeningConditionQO();
				screeningConditionQO.setFetchScreeningCondition(true);
				screeningConditionQO.setOrderBy(1);
				screeningConditionQO.setProductCategoryQO(new ProductCategoryQO());
				screeningConditionQO.getProductCategoryQO().setId(
						productCategory.getId());
				scList = screeningConditionService.queryList(screeningConditionQO);
			}

			List<ScreeningConditionDTO> dtoList = new ArrayList<ScreeningConditionDTO>();
			if (CollectionUtils.isNotEmpty(scList)) {
				for (ScreeningCondition sc : scList) {
					if (StringUtils.equals(sc.getId(), sc.getParent().getId())) {
						ScreeningConditionDTO scDto = new ScreeningConditionDTO();
						List<ScreeningConditionParentDTO> parentList = new ArrayList<ScreeningConditionParentDTO>();
						ScreeningConditionQO screeningConditionQO = new ScreeningConditionQO();
						screeningConditionQO
								.setParendQO(new ScreeningConditionQO());
						screeningConditionQO.getParendQO().setId(sc.getId());
						List<ScreeningCondition> childrenList = screeningConditionService
								.queryList(screeningConditionQO);
						for (ScreeningCondition child : childrenList) {
							if (!StringUtils.equals(child.getName(), child
									.getParent().getName())) {
								ScreeningConditionParentDTO chilDto = new ScreeningConditionParentDTO();
								chilDto.setId(child.getId());
								chilDto.setName(child.getName());
								parentList.add(chilDto);
							}
						}
						scDto.setId(sc.getId());
						scDto.setParentList(parentList);
						scDto.setName(sc.getName());
						dtoList.add(scDto);
					}
				}
			}
			model.addAttribute("scList", dtoList);

		}

		public void getProductBrand(HttpServletRequest request, Model model,NavigationItemQO qo) {

			// 获取分类
			ProductCategory productCategory = getProductCategory(model, qo);
			List<ProductBrand> brandList = null;
			if (null != productCategory) {
				ProductBrandQO brandQO = new ProductBrandQO();
				brandQO.setFetchProductBrand(true);
				brandQO.setOrderBy(1);
				brandQO.setProductCategoryQO(new ProductCategoryQO());
				brandQO.getProductCategoryQO().setId(productCategory.getId());
				brandList = productBrandService.queryList(brandQO);
			}
			
			List<ProductBrandDTO> brandDTOList = new ArrayList<ProductBrandDTO>();
			if (CollectionUtils.isNotEmpty(brandList)) {
				for (ProductBrand brand : brandList) {
					if (StringUtils.equals(brand.getId(), brand.getParent().getId())) {
						ProductBrandDTO brandDTO = new ProductBrandDTO();
						brandDTO.setId(brand.getId());
						brandDTO.setBrandName(brand.getBrandName());
						brandDTO.setSignImage(brand.getSignImage());
						brandDTOList.add(brandDTO);
					}
				}
			}
			model.addAttribute("brandList", brandDTOList);

		}

		
		public ProductCategory getProductCategory(Model model,NavigationItemQO qo) {
			
			NavigationItem navigationItem=null;
			
			if(StringUtils.isNotBlank(qo.getId())){
				navigationItem=navigationItemService.queryUnique(qo);
			}
			ProductCategory productCategory=null;
			ProductCategoryQO productCategoryQO = new ProductCategoryQO();
			if(null!=navigationItem){
				productCategoryQO.setName(navigationItem.getTitle());
				productCategory = productCategoryService.queryUnique(productCategoryQO);
			}else{
				if(StringUtils.isNotBlank(qo.getProductCatoryId())){
					productCategoryQO.setId(qo.getProductCatoryId());
					productCategory = productCategoryService.queryUnique(productCategoryQO);
				}
				
			}
			model.addAttribute("productCategory", productCategory);
			return productCategory;
		}

		
		
		
		public PageUtils getQueryPagtion(PageUtils pageUtils,
				Pagination pagination, ProductQO productQO) {

			
			ProductCategoryQO productCategoryQO = new ProductCategoryQO();
			productCategoryQO.setId(productQO.getProductCategoryId());
			productCategoryQO.setFetchProductCategory(true);
			List<String> ids=new ArrayList<String>();
			List<ProductCategory> pcList=productCategoryService.queryList(productCategoryQO);
			
			if(CollectionUtils.isNotEmpty(pcList)){
				for (ProductCategory productCategory : pcList) {
					ids.add(productCategory.getId());
					ProductCategoryQO childrenQO = new ProductCategoryQO();
					childrenQO.setParendQO(new ProductCategoryQO());
					childrenQO.getParendQO().setId(productCategory.getId());
					List<ProductCategory> childrenList=productCategoryService.queryList(childrenQO);
					if(CollectionUtils.isNotEmpty(childrenList)){
						
						for (ProductCategory productCategory2 : childrenList) {
							if(!StringUtils.equals(productCategory2.getName(), productCategory2.getParent().getName())){
								ids.add(productCategory2.getId());
								
								ProductCategoryQO childQO = new ProductCategoryQO();
								childQO.setParendQO(new ProductCategoryQO());
								childQO.getParendQO().setId(productCategory2.getId());
								List<ProductCategory> childList=productCategoryService.queryList(childQO);
								for (ProductCategory productCategory3 : childList) {
									ids.add(productCategory3.getId());
								}
								
							}
							
						}
					}
					
				}
				
			}
			
			productQO.setProductCategoryQO(new ProductCategoryQO());
			productQO.getProductCategoryQO().setIds(ids);
			productQO.setProductBrandQO(new ProductBrandQO());
			productQO.getProductBrandQO().setId(productQO.getBrandId());
			pagination.setPageNo(Integer.parseInt(productQO.getPage()));
			pagination.setPageSize(productQO.getPageSize());
			pagination.setCondition(productQO);
			pagination = queryPagination(pagination);
			pageUtils = PageUtils.getPageNation(pagination);
			return pageUtils;
		}
		

	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Product> getEntityClass() {
		// TODO Auto-generated method stub
		return Product.class;
	}

}
