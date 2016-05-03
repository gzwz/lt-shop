package lt.admin.entity;

import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import lt.admin.command.CreateResourceCommand;
import lt.admin.command.ModifyResourceCommand;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 一个访问地址链接（功能）
 * 
 * @author wxp
 * 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "RESOURCE")
@SuppressWarnings("serial")
public class Resource extends StringIdBaseEntity {

	/**
	 * 资源所属客户端类型
	 */
	@Column(name = "CLIENT_TYPE")
	private String clientType;

	@Column(name = "URL")
	private String url;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ICON")
	private String icon;
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_RESOURCE_ID")
	private Resource parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.REMOVE })
	private Set<Resource> children;

	@Column(name = "REMARK", length = 512)
	private String remark;
	
	@Column(name = "SORT", length = 512)
	private Integer sort;
	

	public void create(CreateResourceCommand command) {
		setId(UUIDGenerator.getUUID());
		
		setName(command.getName());
		setUrl(command.getUrl());
		setRemark(command.getRemark());
		setClientType("Admin");
		setIcon(command.getIcon());
		setSort(command.getSort());
		
	}

	public void modify(ModifyResourceCommand command) {
		
		setName(command.getName());
		setUrl(command.getUrl());
		setRemark(command.getRemark());
		setIcon(command.getIcon());
		setSort(command.getSort());
	}
	
	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Set<Resource> getChildren() {
		return children;
	}

	public void setChildren(Set<Resource> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
}
