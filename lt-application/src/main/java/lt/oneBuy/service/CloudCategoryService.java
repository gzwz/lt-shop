package lt.oneBuy.service;

import lt.oneBuy.command.CreateCloudCategoryCommand;
import lt.oneBuy.command.ModifyCloudCategoryCommand;
import lt.oneBuy.entity.CloudCategory;
import lt.oneBuy.qo.CloudCategoryQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CloudCategoryService extends BaseDao<CloudCategory, CloudCategoryQO>{

	public CloudCategory createCloudCategory(CreateCloudCategoryCommand command){
		
		CloudCategory parent=get(command.getParentId());
		
		CloudCategory cloudCategory=new CloudCategory();
		cloudCategory.create(command, parent);
		
		if(null==parent){
			cloudCategory.setParent(cloudCategory);
		}
		save(cloudCategory);
		return cloudCategory;
	}
	
	public CloudCategory modify(ModifyCloudCategoryCommand command){
		
		CloudCategory parent=get(command.getParentId());
		
		CloudCategory cloudCategory=get(command.getCloudCategoryId());
		cloudCategory.modify(command, parent);
		if(null==parent){
			cloudCategory.setParent(cloudCategory);
		}
		update(cloudCategory);
		return cloudCategory;
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, CloudCategoryQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<CloudCategory> getEntityClass() {
		// TODO Auto-generated method stub
		return CloudCategory.class;
	}

}
