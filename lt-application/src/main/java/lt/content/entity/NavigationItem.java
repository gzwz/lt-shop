package lt.content.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lt.base.entity.DomainLink;
import lt.content.command.CreateNavigationItemCommand;
import lt.content.command.ModifyNavigationItemCommand;

/**
 * 导航菜单项
 * 
 * @author yuxx
 *
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "NAVIGATION_ITEM")
@SuppressWarnings("serial")
public class NavigationItem extends StringIdBaseEntity {

	/**
	 * 标题
	 */
	@Column(name = "TITLE", length = 256)
	private String title;

	/**
	 * 排序
	 */
	@Column(name = "SORT", length = 8)
	private Integer sort;

	/**
	 * 链接
	 */
	@Column(name = "URL", length = 256)
	private String url;

	/**
	 * 链接的业务领域
	 */
	private DomainLink domainLink;

	public void create(CreateNavigationItemCommand command) {
		setId(UUIDGenerator.getUUID());
		
		setTitle(command.getTitle());
		setSort(command.getSort());
		setUrl(command.getUrl());
	}

	public void modify(ModifyNavigationItemCommand command) {
		setTitle(command.getTitle());
		setSort(command.getSort());
		setUrl(command.getUrl());
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}

}
