package lt.articlecate.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.articlecate.command.CreateArticleCateCommand;
import lt.articlecate.command.ModifyArticleCateCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ARTICLECATE")
public class ArticleCate extends StringIdBaseEntity {

	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;

	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_article_cate_ID")
	private ArticleCate parent;

	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = { CascadeType.REMOVE })
	private Set<ArticleCate> children;
	
	@Column(name = "sort", length = 512)
	private Integer sort;

	
	@Column( name = "create_time")
	private Date createTime;
	

	public void create(CreateArticleCateCommand command) {

		setId(UUIDGenerator.getUUID());
		setName(command.getName());
		setCreateTime(new Date());
		setSort(command.getSort());

	}

	public void modify(ModifyArticleCateCommand command) {

		setName(command.getName());
		setSort(command.getSort());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArticleCate getParent() {
		return parent;
	}

	public void setParent(ArticleCate parent) {
		this.parent = parent;
	}

	public Set<ArticleCate> getChildren() {
		return children;
	}

	public void setChildren(Set<ArticleCate> children) {
		this.children = children;
	}

 
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	

}
