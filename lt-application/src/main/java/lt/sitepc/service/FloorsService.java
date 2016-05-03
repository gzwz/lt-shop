package lt.sitepc.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gzlazypack.common.component.BaseDao;
import lt.ad.service.AdService;
import lt.base.service.BannerService;
import lt.merchant.service.MerchantContactInfoService;
import lt.merchant.service.MerchantService;
import lt.oneBuy.service.OneBuyService;
import lt.product.service.ProductBrandService;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductService;
import lt.product.service.ScreeningConditionService;
import lt.sitepc.DTO.FloorsDTO;
import lt.sitepc.DTO.FloorsParentDTO;
import lt.sitepc.command.CreateFloorsCommand;
import lt.sitepc.command.ModifyFloorsCommand;
import lt.sitepc.entity.Floors;
import lt.sitepc.qo.FloorsQO;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;



@Service
@Transactional
public class FloorsService extends BaseDao<Floors, FloorsQO> {
	
	/**
	 * service注解
	 */
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OneBuyService oneBuyService;
	
	
	@Autowired
	private AdService adService;

	/**
	 * service注解
	 */
	@Autowired
	private BannerService bannerService;


	/** service */
	@Autowired
	private ProductCategoryService productCategoryService;

	/** service */
	@Autowired
	private ProductBrandService productBrandService;

	/** service */
	@Autowired
	private ScreeningConditionService screeningConditionService;
	
	/** 店铺service */
	@Autowired
	private MerchantService merchantService;

	
	@Autowired
	private MerchantContactInfoService merchantContactInfoService;
	
	public Floors createFloors(CreateFloorsCommand command){
		
	/*	CollectionUtils.isNotEmpty(command.getProductIds())*/
		Floors parent=get(command.getParentId());
		
		 Floors	floors=new Floors();
			floors.create(command, parent);
			if(null==parent){
				floors.setParent(floors);
			}
			save(floors);
		return floors;
	}

	public Floors modifyFloors(ModifyFloorsCommand command){
		
		Floors parent=get(command.getParentId());
		
		Floors floors=get(command.getFloorsId());
		floors.modify(command, parent);
		if(null==parent){
			floors.setParent(floors);
		}
		update(floors);
		return floors;
	}
	
	
	public void getFloors(HttpServletRequest request, Model model) {

		FloorsQO qo = new FloorsQO();
		qo.setOrderBy(1);
		List<Floors> floorsList = queryList(qo);
		List<FloorsDTO> floorsDTO = new ArrayList<FloorsDTO>();
		for (Floors floors : floorsList) {
			if (StringUtils.equals(floors.getId(), floors.getParent().getId())) {
				FloorsDTO pcator = new FloorsDTO();
				List<FloorsParentDTO> parentDTO = new ArrayList<FloorsParentDTO>();

				FloorsQO parentQO = new FloorsQO();
				parentQO.setOrderBy(1);
				parentQO.setParentQO(new FloorsQO());
				parentQO.getParentQO().setId(floors.getId());
				List<Floors> parentList = queryList(parentQO);
				for (Floors pdct : parentList) {
					if (!StringUtils.equals(pdct.getName(), pdct.getParent()
							.getName())) {
						FloorsParentDTO pDTO = new FloorsParentDTO();
						pDTO.setId(pdct.getId());
						pDTO.setName(pdct.getName());
						pDTO.setUrl(pdct.getUrl());
						pDTO.setPrice(pdct.getPrice());
						pDTO.setTitleImage(pdct.getTitleImage());
						parentDTO.add(pDTO);
					}
				}
				pcator.setId(floors.getId());
				pcator.setName(floors.getName());
				pcator.setUrl(floors.getUrl());
				pcator.setPrice(floors.getPrice());
				pcator.setTitleImage(floors.getTitleImage());
				pcator.setParentList(parentDTO);
				floorsDTO.add(pcator);
			}
		}
		model.addAttribute("floorsList", floorsDTO);
	}
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, FloorsQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Floors> getEntityClass() {
		// TODO Auto-generated method stub
		return Floors.class;
	}

}
