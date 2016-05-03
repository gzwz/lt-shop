package lt.merchant.entity;

import java.util.Date;
import java.util.Set;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;









import lt.merchant.command.CreateMerchantCategoryCommand;
import lt.merchant.command.ModifyMerchantCategoryCommand;

import org.hibernate.annotations.DynamicUpdate;


@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "MERCHANTCATEGORY")
public class MerchantCategory extends StringIdBaseEntity{

	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	
	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_merchant_category_ID")
	private MerchantCategory parent;
	
	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent",cascade = { CascadeType.REMOVE })
	private Set<MerchantCategory> children;
	
	@Column(name = "create_time")
	private Date createTime;
	

	
	public void create(CreateMerchantCategoryCommand command){
		
		setId(UUIDGenerator.getUUID());
		setName(command.getName());
		setCreateTime(new Date());
		
		
		
	}

	
	public void modify(ModifyMerchantCategoryCommand command){
		
		setName(command.getName());
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public MerchantCategory getParent() {
		return parent;
	}


	public void setParent(MerchantCategory parent) {
		this.parent = parent;
	}


	public Set<MerchantCategory> getChildren() {
		return children;
	}


	public void setChildren(Set<MerchantCategory> children) {
		this.children = children;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
