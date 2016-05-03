package lt.sitepc.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.sitepc.command.CreateBottoMenuCommand;
import lt.sitepc.command.ModifyBottomMenuCommand;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "BOTTOMMENU")
@SuppressWarnings("serial")
public class BottomMenu extends StringIdBaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "icon")
	private String icon;

	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parendt_bm_id")
	private BottomMenu parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = { CascadeType.REMOVE })
	private Set<BottomMenu> children;

	@Column(name = "SORT", length = 512)
	private Integer sort;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="bottomMenu",cascade={CascadeType.ALL})
	private Set<MenuContents> menuContents;

	public void create(CreateBottoMenuCommand command, BottomMenu parent) {

		setId(UUIDGenerator.getUUID());

		setIcon(command.getIcon());
		setName(command.getName());
		setSort(command.getSort());
		setParent(parent);

	}

	public void modify(ModifyBottomMenuCommand command, BottomMenu parent) {

		setIcon(command.getIcon());
		setName(command.getName());
		setSort(command.getSort());
		setParent(parent);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public BottomMenu getParent() {
		return parent;
	}

	public void setParent(BottomMenu parent) {
		this.parent = parent;
	}

	public Set<BottomMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<BottomMenu> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<MenuContents> getMenuContents() {
		return menuContents;
	}

	public void setMenuContents(Set<MenuContents> menuContents) {
		this.menuContents = menuContents;
	}

}
