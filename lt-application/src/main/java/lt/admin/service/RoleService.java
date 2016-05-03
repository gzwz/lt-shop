package lt.admin.service;

import gzlazypack.common.component.BaseDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.admin.command.CreateRoleCommand;
import lt.admin.command.DeleteRoleCommand;
import lt.admin.command.ModifyRoleCommand;
import lt.admin.entity.Resource;
import lt.admin.entity.Role;
import lt.admin.qo.ResourceQO;
import lt.admin.qo.RoleQO;

@Service
@Transactional
public class RoleService extends BaseDao<Role, RoleQO> {
	
	@Autowired
	private ResourceService resourceServiceImpl;
	
	public Role createRole(CreateRoleCommand command) {
		
		Set<Resource> resources = queryResources(command.getResourceIds());
		
		Role role = new Role();
		role.create(command, resources);
		
		save(role);
		return role;
	}
	
	public Role modifyRole(ModifyRoleCommand command) {
		Set<Resource> resources = queryResources(command.getResourceIds());
		
		Role role = get(command.getRoleId());
		role.modify(command, resources);
		
		update(role);
		return role;
	}
	
	private Set<Resource> queryResources(List<String> resourceIds) {
		ResourceQO qo = new ResourceQO();
		qo.setIds(resourceIds);
		List<Resource> resourceList = resourceServiceImpl.queryList(qo);
		
		Set<Resource> resources = new HashSet<>();
		
		if (resourceList == null) {
			return resources;
		}
		
		for (Resource resource : resourceList) {
			resources.add(resource);
		}
		return resources;
	}

	public void deleteRole(DeleteRoleCommand command) {
		
		deleteById(command.getRoleId());
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, RoleQO qo) {
		return criteria;
	}

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

}
