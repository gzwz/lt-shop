package lt.merchant.service;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
import lt.merchant.entity.Merchant;
import lt.merchant.entity.MerchantAuthenticateInfo;
import lt.merchant.qo.MerchantAuthenticateInfoQO;
import gzlazypack.common.component.BaseDao;
@Service
@Transactional
public class MerchantAuthenticateInfoService extends BaseDao<MerchantAuthenticateInfo, MerchantAuthenticateInfoQO>{

	@Autowired
	private MerchantService merchantService;
	
	public MerchantAuthenticateInfo createMerchantAuthenticateInfo(CreateMerchantCommand command,Merchant merchant){
		MerchantAuthenticateInfo info=new MerchantAuthenticateInfo();
		info.create(command, merchant);
		save(info);
		return info;
	}
	
 
	public MerchantAuthenticateInfo modifyMerchantAuthenticateInfo(ModifyMerchantCommand command){
		
		Merchant merchant=merchantService.get(command.getMerchantId());
		
		MerchantAuthenticateInfo info=get(command.getMerchantAuthenticateInfoId());
		info.modify(command, merchant);
		update(info);
		return info;
	}
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria,
			MerchantAuthenticateInfoQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<MerchantAuthenticateInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return MerchantAuthenticateInfo.class;
	}

}
