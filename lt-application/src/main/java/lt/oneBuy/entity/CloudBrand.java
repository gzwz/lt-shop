package lt.oneBuy.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import lt.oneBuy.command.CreateCloudBrandCommand;
import lt.oneBuy.command.ModifyCloudBrandCommand;
import lt.utils.PinyingUtil;

import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.annotation.JSONField;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "CLOUDBRAND")
public class CloudBrand extends StringIdBaseEntity {

	/**
	 * 品牌名称
	 */
	@Column(name = "brand_name", length = 64)
	private String brandName;

	/**
	 * 拼音
	 */
	@Column(name = "pinyin", length = 64)
	private String pinyin;

	/**
	 * 父类目
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_cloud_brand_id")
	@JSONField(serialize = false)
	private CloudBrand parent;

	@Column(name = "sort", length = 512)
	private Integer sort;

	/**
	 * 子类目
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = { CascadeType.ALL })
	@JSONField(serialize = false)
	private Set<CloudBrand> children;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = M.TABLE_PREFIX + "cloudBrand_cloudCategory", joinColumns = { @JoinColumn(name = "cloudBrand_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "cloudCategory_id", referencedColumnName = "ID") })
	private Set<CloudCategory> cloudCategorys;

	public void create(CreateCloudBrandCommand command,
			Set<CloudCategory> cloudCategorys, CloudBrand parent) {

		setId(UUIDGenerator.getUUID());

		setBrandName(command.getBrandName());
		setCloudCategorys(cloudCategorys);
		setSort(command.getSort());
		setParent(parent);
		setPinyin(PinyingUtil.getHeadByString(command.getBrandName())[0]);
	}

	public void modify(ModifyCloudBrandCommand command,
			Set<CloudCategory> cloudCategorys, CloudBrand parent) {

		setBrandName(command.getBrandName());
		setCloudCategorys(cloudCategorys);
		setSort(command.getSort());
		setParent(parent);
		setPinyin(PinyingUtil.getHeadByString(command.getBrandName())[0]);
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public CloudBrand getParent() {
		return parent;
	}

	public void setParent(CloudBrand parent) {
		this.parent = parent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Set<CloudBrand> getChildren() {
		return children;
	}

	public void setChildren(Set<CloudBrand> children) {
		this.children = children;
	}

	public Set<CloudCategory> getCloudCategorys() {
		return cloudCategorys;
	}

	public void setCloudCategorys(Set<CloudCategory> cloudCategorys) {
		this.cloudCategorys = cloudCategorys;
	}

}
