package lt.merchant.service;

import gzlazypack.common.component.BaseDao;
import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
import lt.merchant.entity.Merchant;
import lt.merchant.entity.MerchantContactInfo;
import lt.merchant.qo.MerchantContactInfoQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantContactInfoService extends BaseDao<MerchantContactInfo, MerchantContactInfoQO>{
	
	@Autowired
	private MerchantService merchantService;
	
	public MerchantContactInfo createMerchantContactInfo(CreateMerchantCommand command,Merchant merchant,Province province,City city,Area area){
		
		MerchantContactInfo info=new MerchantContactInfo();
		info.create(command, merchant, province, city, area);
		save(info);
		return info;
	}
	
	public MerchantContactInfo modifyMerchantContactInfo(ModifyMerchantCommand command,Province province,City city,Area area){
		
		Merchant merchant=merchantService.get(command.getMerchantId());
		
		MerchantContactInfo info=get(command.getMerchantContactInfoId());
		info.modify(command, merchant, province, city, area);
		update(info);
		return info;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, MerchantContactInfoQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<MerchantContactInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return MerchantContactInfo.class;
	}

}
