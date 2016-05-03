package lt.marketing.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.marketing.command.CreateMarketingEntryCommand;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 推广入口 一个人/商家，针对一个被推广对象，在某个传播渠道上，创建了一个入口，自然形成一个推广链接和二维码
 * 
 * 
 */
@SuppressWarnings("serial")
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PROMOTION_ENTRY")
public class MarketingEntry extends StringIdBaseEntity {

	/**
	 * 简介
	 */
	@Column(name = "INTRO", length = 512)
	private String intro;

	/**
	 * 推广主体，推广人
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT", nullable = false)
	private MarketingSubject subject;

	/**
	 * 被推广目标对象
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGET_ID", nullable = false)
	private MarketingTarget target;

	/**
	 * 创建日期
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	/**
	 * 生成令牌数
	 */
	@Column(name = "TOKEN_NUM", columnDefinition = M.LONG_NUM_COLUMN)
	private Integer tokenNum;

	public void create(CreateMarketingEntryCommand command, MarketingSubject subject, MarketingTarget target) {
		setId(UUIDGenerator.getUUID());
		
		setIntro(command.getIntro());
		setSubject(subject);
		setTarget(target);
		
		setTokenNum(0);
		setCreateDate(new Date());
	}

	public MarketingSubject getSubject() {
		return subject;
	}

	public void setSubject(MarketingSubject subject) {
		this.subject = subject;
	}

	public MarketingTarget getTarget() {
		return target;
	}

	public void setTarget(MarketingTarget target) {
		this.target = target;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getTokenNum() {
		return tokenNum;
	}

	public void setTokenNum(Integer tokenNum) {
		this.tokenNum = tokenNum;
	}

}
