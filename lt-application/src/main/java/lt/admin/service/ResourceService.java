package lt.admin.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.admin.command.CreateResourceCommand;
import lt.admin.command.DeleteResourceCommand;
import lt.admin.command.ModifyResourceCommand;
import lt.admin.entity.Resource;
import lt.admin.qo.ResourceQO;

@Service
@Transactional
public class ResourceService extends BaseDao<Resource, ResourceQO> {

	public Resource createResource(CreateResourceCommand command) {

		Resource resource = new Resource();
		resource.create(command);
		if (command.getParentId() != null && !"".equals(command.getParentId())) {
			Resource Resource1 = new Resource();
			Resource1.setId(command.getParentId());
			resource.setParent(Resource1);
		} else {
			resource.setParent(resource);
		}
		save(resource);
		return resource;
	}

	public Resource modifyResource(ModifyResourceCommand command) {

		Resource resource = get(command.getResourceId());
		resource.modify(command);
		if (command.getParentId() != null && !"".equals(command.getParentId())) {
			Resource Resource1 = new Resource();
			Resource1.setId(command.getParentId());
			resource.setParent(Resource1);
		} else {
			resource.setParent(resource);
		}
		
		update(resource);
		return resource;
	}

	public void deleteResource(DeleteResourceCommand command) {
		deleteById(command.getResourceId());
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ResourceQO qo) {
		return criteria;
	}

	@Override
	protected Class<Resource> getEntityClass() {
		return Resource.class;
	}

}
