package lt.marketing.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.base.entity.DomainLink;
import lt.marketing.command.CreateMarketingEntryCommand;

import org.hibernate.annotations.DynamicUpdate;


/**
 * 被推广对象
 * 
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PROMOTION_TARGET")
public class MarketingTarget extends StringIdBaseEntity {
	/**
	 * 完整地址绝对url 比如推广商品，就填写商品的正常访问url，如http://www.xxx.com/product/1
	 * 但是在发给用户时，是发推广链接，如http://marketing.xxx.com/BK32A9CH
	 * 业务系统收到访问请求以后使用BK32A9CH调用推广模块接口查询重定向链接，即该url值
	 * 推广模块同时会生成一个MarketingToken返回给业务系统，用于分配给进入网站的用户
	 * BK32A9CH是PromotionEntry的id，入口id，一个推广对象可以有多个入口
	 */
	@Column(name = "URL", length = 256)
	private String url;

	/**
	 * 名称
	 */
	@Column(name = "NAME", length = 128)
	private String name;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 512)
	private String remark;

	/**
	 * 被推广对象在业务系统的实体
	 */
	private DomainLink domainLink;

	public void create(CreateMarketingEntryCommand command) {
		setId(UUIDGenerator.getUUID());
		
		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(command.getTargetId());
		getDomainLink().setDomainName(command.getTargetName());
		getDomainLink().setDomainType(command.getTargetType());
		
		setName(command.getTargetName());
		setUrl(command.getTargetUrl());
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

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}

}
