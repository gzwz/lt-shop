package lt.admin.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lt.admin.command.CreateRoleCommand;
import lt.admin.command.ModifyRoleCommand;

/**
 * 角色
 * 
 * @author wxp
 * 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ROLE")
@SuppressWarnings("serial")
public class Role extends StringIdBaseEntity {

	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 排序
	 */
	@Column(name = "SORT", length = 4)
	private Integer sort;

	/**
	 * 拥有授权的功能
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "ROLE_RESOURCE", joinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID") })
	private Set<Resource> resources;

	public void create(CreateRoleCommand command, Set<Resource> resources) {
		setId(UUIDGenerator.getUUID());

		setName(command.getName());
		setSort(command.getSort());
		setResources(resources);
	}

	public void modify(ModifyRoleCommand command, Set<Resource> resources2) {
		
		setName(command.getName());
		setSort(command.getSort());
		setResources(resources2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
