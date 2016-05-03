package lt.marketing.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.entity.DomainLink;
import lt.marketing.command.CreateMarketingSubjectCommand;

import org.hibernate.annotations.DynamicUpdate;


/**
 * 推广主体 可以是用户、商户、平台、业务员等
 * 
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PROMOTION_SUBJECT")
public class MarketingSubject extends StringIdBaseEntity {

	/**
	 * 业务系统主体
	 */
	private DomainLink domainLink;


	/**
	 * 上线推广员
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", nullable = true)
	private MarketingSubject parent;

	public void create(CreateMarketingSubjectCommand command,
			MarketingSubject subject) {
		setId(UUIDGenerator.getUUID());

		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(command.getSubjectId());
		getDomainLink().setDomainName(command.getSubjectName());
		getDomainLink().setDomainType(command.getSubjectType());

		setParent(subject);
		
	}

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}


	public MarketingSubject getParent() {
		return parent;
	}

	public void setParent(MarketingSubject parent) {
		this.parent = parent;
	}

}
