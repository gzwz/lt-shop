package lt.oneBuy.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lt.oneBuy.command.CreateCloudBrandCommand;
import lt.oneBuy.command.ModifyCloudBrandCommand;
import lt.oneBuy.entity.CloudBrand;
import lt.oneBuy.entity.CloudCategory;
import lt.oneBuy.qo.CloudBrandQO;
import lt.oneBuy.qo.CloudCategoryQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CloudBrandService  extends BaseDao<CloudBrand, CloudBrandQO>{
	
	@Autowired
	private CloudCategoryService cloudCategoryService;
	
	public CloudBrand createCloudBrand(CreateCloudBrandCommand command){
		
		Set<CloudCategory> cloudCategorys=queryCloudCategory(command.getCloudCategoryIds());
		
		CloudBrand parent=get(command.getParentId());
		
		CloudBrand cloudBrand=new CloudBrand();
		cloudBrand.create(command, cloudCategorys, parent);
		if(null==parent){
			cloudBrand.setParent(cloudBrand);
		}
		save(cloudBrand);
		return cloudBrand;
	}
	
	
	public CloudBrand modifyCloudBrand(ModifyCloudBrandCommand command){
		
	   Set<CloudCategory> cloudCategorys=queryCloudCategory(command.getCloudCategoryIds());
		
		CloudBrand parent=get(command.getParentId());
		
		CloudBrand cloudBrand=get(command.getCloudBrandId());
		cloudBrand.modify(command, cloudCategorys, parent);
		if(null==parent){
			cloudBrand.setParent(cloudBrand);
		}
		update(cloudBrand);
		return cloudBrand;
	}

	
	private Set<CloudCategory> queryCloudCategory(List<String> cloudCategoryIds) {
		CloudCategoryQO qo = new CloudCategoryQO();
		qo.setIds(cloudCategoryIds);
		List<CloudCategory> cloudCategoryList = cloudCategoryService.queryList(qo);
		
		Set<CloudCategory> cloudCategorys = new HashSet<CloudCategory>();
		
		if (cloudCategoryList == null) {
			return cloudCategorys;
		}
		
		for (CloudCategory cloudCategory : cloudCategoryList) {
			cloudCategorys.add(cloudCategory);
		}
		return cloudCategorys;
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, CloudBrandQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<CloudBrand> getEntityClass() {
		// TODO Auto-generated method stub
		return CloudBrand.class;
	}

}
