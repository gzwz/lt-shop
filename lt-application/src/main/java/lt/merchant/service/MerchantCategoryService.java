package lt.merchant.service;

import lt.merchant.command.CreateMerchantCategoryCommand;
import lt.merchant.command.ModifyMerchantCategoryCommand;
import lt.merchant.entity.MerchantCategory;
import lt.merchant.qo.MerchantCategoryQO;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.ResultJSON;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MerchantCategoryService extends BaseDao<MerchantCategory, MerchantCategoryQO>{

	public String createMerchantCategory(CreateMerchantCategoryCommand command){
		try {
			MerchantCategory merchantCategory=new MerchantCategory();
			merchantCategory.create(command);
			if(StringUtils.isNotBlank(command.getParentId())){
				MerchantCategory merchantCategory1=new MerchantCategory();
				merchantCategory1.setId(command.getParentId());
				merchantCategory.setParent(merchantCategory1);
			}else{
				merchantCategory.setParent(merchantCategory);
			}
			save(merchantCategory);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "类型新增失败");
		}
		
		return ResultJSON.resultToJSONStr(true, "类型新增成功");
	}
	
	public String modifyMerchantCategory(ModifyMerchantCategoryCommand command) {
		try {
			MerchantCategory merchantCategory=get(command.getMerchantCategoryId());
			merchantCategory.modify(command);
			if(StringUtils.isNotBlank(command.getParentId())){
				MerchantCategory merchantCategory1=new MerchantCategory();
				merchantCategory1.setId(command.getParentId());
				merchantCategory.setParent(merchantCategory1);
			}else{
				merchantCategory.setParent(merchantCategory);
			}
			
			update(merchantCategory);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "类型修改失败");
		}
		
		return ResultJSON.resultToJSONStr(true, "类型修改成功");
	}
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, MerchantCategoryQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<MerchantCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return MerchantCategory.class;
	}

}
