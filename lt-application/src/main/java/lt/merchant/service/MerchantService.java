package lt.merchant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lt.base.command.CreateImageCommand;
import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Image;
import lt.base.entity.Province;
import lt.base.qo.ImageQO;
import lt.base.service.AreaService;
import lt.base.service.CityService;
import lt.base.service.ImageService;
import lt.base.service.ProvinceService;
import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
import lt.merchant.entity.Merchant;
import lt.merchant.entity.MerchantCategory;
import lt.merchant.entity.ValidateStatus;
import lt.merchant.qo.MerchantCategoryQO;
import lt.merchant.qo.MerchantQO;
import lt.user.entity.User;
import lt.user.entity.UserStatus;
import lt.user.service.UserService;
import gzlazypack.common.component.BaseDao;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantService extends BaseDao<Merchant, MerchantQO>{
	
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private MerchantCategoryService merchantCategoryService;
	
	@Autowired
	private MerchantAuthenticateInfoService merchantAuthenticateInfoService;
	
	@Autowired
	private MerchantContactInfoService merchantContactInfoService;
	

	public Merchant createMerchant(CreateMerchantCommand command) {
		
		User user=userService.get(command.getUserId());
		
		user.setType(command.getType());
		user.getStatus().setValidateShop(UserStatus.VALIDATE_APPLY);
		userService.update(user);
		
		Province province=provinceService.get(command.getProvinceId());
		
		City city=cityService.get(command.getCityId());
		
		Area area=areaService.get(command.getAreaId());
		
		MerchantCategory merchantCategory=merchantCategoryService.get(command.getMerchantCategoryId());
		
		Merchant merchant=new Merchant();
		merchant.create(command,user, merchantCategory);
		save(merchant);
		
		if(StringUtils.isNotBlank(command.getImages())){
			// 图片处理
			Map<String, String> specImageMap = new HashMap<String, String>();
			if (StringUtils.isNoneBlank(command.getImages())) {
				for (int i = 0; i < command.getImages().split(",").length; i++) {
					specImageMap.put("shop_image" + i, command.getImages()
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
						createImageCommand.setDomainLinkId(merchant.getId());
						createImageCommand.setDomainLinkName(merchant.getBaseInfo().getName());
						createImageCommand.setDomainLinkType("shop_image");
						createImageCommand.setSort(index);
						imageService.createImage(createImageCommand);
						index++;
					}
				}
			}
		}
		
		
		merchantAuthenticateInfoService.createMerchantAuthenticateInfo(command, merchant);
		
		merchantContactInfoService.createMerchantContactInfo(command, merchant, province, city, area);
		
		return merchant;
	}
	
	
	@SuppressWarnings("unused")
	private Set<MerchantCategory> queryMerchantCategory(List<String> merchantCategoryIds) {
		MerchantCategoryQO qo = new MerchantCategoryQO();
		qo.setIds(merchantCategoryIds);
		List<MerchantCategory> merchantCategoryList = merchantCategoryService.queryList(qo);
		
		Set<MerchantCategory> merchantCategorys = new HashSet<MerchantCategory>();
		
		if (merchantCategoryList == null) {
			return merchantCategorys;
		}
		
		for (MerchantCategory merchantCategory : merchantCategoryList) {
			merchantCategorys.add(merchantCategory);
		}
		return merchantCategorys;
	}	
	
	
	
	
	public Merchant  modifyMerchant(ModifyMerchantCommand command) {
		
        Province province=provinceService.get(command.getProvinceId());
		
		City city=cityService.get(command.getCityId());
		
		Area area=areaService.get(command.getAreaId());
		

		MerchantCategory merchantCategory=merchantCategoryService.get(command.getMerchantCategoryId());
		
		Merchant merchant=get(command.getMerchantId());
		merchant.modifyMerchant(command, merchantCategory);
		update(merchant);
		
		ImageQO imgeQO = new ImageQO();
		imgeQO.setDomainId(merchant.getId());
		Image image=imageService.queryUnique(imgeQO);
		if(null!=image){
			imageService.delete(image);
		}
		
		
		if(StringUtils.isNotBlank(command.getImages())){
			// 图片处理
			Map<String, String> specImageMap = new HashMap<String, String>();
			if (StringUtils.isNoneBlank(command.getImages())) {
				for (int i = 0; i < command.getImages().split(",").length; i++) {
					specImageMap.put("shop_image" + i, command.getImages()
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
						createImageCommand.setDomainLinkId(merchant.getId());
						createImageCommand.setDomainLinkName(merchant.getBaseInfo().getName());
						createImageCommand.setDomainLinkType("shop_image");
						createImageCommand.setSort(index);
						imageService.createImage(createImageCommand);
						index++;
					}
				}
			}
		}
		
		
		merchantAuthenticateInfoService.modifyMerchantAuthenticateInfo(command);
		
		merchantContactInfoService.modifyMerchantContactInfo(command, province, city, area);
		
		return merchant;
	}
	
	 
	
	
	/**
	 * @param id
	 * @param sign
	 */
	public void modityStatus(String id, String sign) {
		// TODO Auto-generated method stub
		Merchant merchant = get(id);
		ValidateStatus status = merchant.getStatus();
		switch (sign) {
		case "front":
			status.setFrontValid(true);
			break;
		case "opposite":
			status.setOppositeValid(true);
			break;
		case "identifyImage":
			status.setIdentifyValid(true);
			break;
		case "charterImage":
			status.setCharterValid(true);
			break;
		case "organizationsImage":
			status.setOrganizationsValid(true);
			break;
		case "registrationImage":
			status.setRegistrationValid(true);
			break;
		case "aptitudesImage":
			status.setAptitudesValid(true);
			break;
//			全局通过
		case "validate_pass":
			merchant.getDomainLink().setDomainType("validate_pass");
			break;
			
			
		}
		try {
			//获得领域id 来更爱用户领域状态
			userService.modifyDomainLink(merchant.getDomainLink().getDomainId(), sign);
			update(merchant);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, MerchantQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Merchant> getEntityClass() {
		// TODO Auto-generated method stub
		return Merchant.class;
	}





}
