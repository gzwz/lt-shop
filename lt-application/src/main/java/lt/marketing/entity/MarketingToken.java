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

import lt.marketing.command.EnterMarketingEntryCommand;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


/**
 * 推广令牌，有人进入推广入口以后向其发放一个，由业务系统持有，可按需放置于cookie，session或数据库用户信息中，用于后续行为消息推送
 * 将该令牌抛弃后即无法再向推广模块推送该用户行为记录
 * 
 * @author yuxiaoxiang
 * 
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "PROMOTION_TOKEN")
public class MarketingToken extends StringIdBaseEntity {

	/**
	 * 推广入口 每个入口有一个人点击进入时就会发一个令牌
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTRY_ID", nullable = false)
	private MarketingEntry entry;

	/**
	 * 是否已停用，被停用的令牌将不再接受新的用户行为消息
	 */
	@Type(type = "yes_no")
	@Column(name = "CLOSE")
	private Boolean close;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	public void create(EnterMarketingEntryCommand command, MarketingEntry entry) {
		setId(UUIDGenerator.getUUID());
		
		setEntry(entry);
		setClose(false);
		setCreateDate(new Date());
	}

	public MarketingEntry getEntry() {
		return entry;
	}

	public void setEntry(MarketingEntry entry) {
		this.entry = entry;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
