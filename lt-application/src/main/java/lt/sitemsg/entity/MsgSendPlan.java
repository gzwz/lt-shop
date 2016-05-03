package lt.sitemsg.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import lt.sitemsg.command.sendplan.CreateMsgSendPlanCommand;
import lt.sitemsg.command.sendplan.ModifyMsgSendPlanCommand;

/**
 * 站内消息发送计划
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "SEND_PLAN")
@SuppressWarnings("serial")
public class MsgSendPlan extends StringIdBaseEntity {

	/**
	 * 发送时间
	 */
	@Column(name = "SEND_DATE", columnDefinition = M.DATE_COLUMN)
	private Date sendDate;

	/**
	 * 发送的用户
	 */
	@Transient
	private String[] userIds;

	/**
	 * 发达的用户数组JSON持久化
	 */
	@Column(name = "USER_IDS_JSON", columnDefinition = M.TEXT_COLUMN)
	private String userIdsJSON;

	/**
	 * 用的模版 如：尊敬的{P}您好，本周末将举行{C}活动，地址是{C}，欢迎光临，你的优惠码是{P}
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = false)
	private MsgTemplate template;

	/**
	 * 在发送时套在模版里的变量集，每个接收者都是用这套变量套出正文 如 ['夏日促销','人民广场110号'] sendDate发送时将替换{C}
	 */
	@Transient
	private String[] params;

	/**
	 * 变量集JSON化存储
	 */
	@Column(name = "PARAMS_JSON", columnDefinition = M.TEXT_COLUMN)
	private String paramsJSON;

	/**
	 * 消息过期时间，超过该时间未看到notice则删除消息
	 */
	@Column(name = "PAST_DUE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date pastDueDate;

	public void create(CreateMsgSendPlanCommand command, MsgTemplate template) {
		setId(UUIDGenerator.getUUID());

		setParams(command.getParams());
		setTemplate(template);
		setUserIds(command.getUserIds());
		setSendDate(command.getSendDate());
		setPastDueDate(command.getPastDueDate());
	}

	public void modify(ModifyMsgSendPlanCommand command, MsgTemplate template) {
		setParams(command.getParams());
		setTemplate(template);
		setSendDate(command.getSendDate());
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String[] getUserIds() {
		if (StringUtils.isNotBlank(userIdsJSON) && userIds == null) {
			List<String> list = JSON.parseArray(userIdsJSON, String.class);
			setUserIds(new String[list.size()]);
			list.toArray(userIds);
		}
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
		if (StringUtils.isBlank(userIdsJSON) && getUserIds() != null) {
			setUserIdsJSON(JSON.toJSONString(userIds));
		}
	}

	public String getUserIdsJSON() {
		if (StringUtils.isBlank(userIdsJSON) && getUserIds() != null) {
			setUserIdsJSON(JSON.toJSONString(getUserIds()));
		}
		return userIdsJSON;
	}

	public void setUserIdsJSON(String userIdsJSON) {
		this.userIdsJSON = userIdsJSON;
	}

	public MsgTemplate getTemplate() {
		return template;
	}

	public void setTemplate(MsgTemplate template) {
		this.template = template;
	}

	public String[] getParams() {
		if (params == null && StringUtils.isNotBlank(paramsJSON)) {
			List<String> list = JSON.parseArray(paramsJSON, String.class);
			setParams(new String[list.size()]);
			list.toArray(params);
		}
		return params;
	}

	public void setParams(String[] params) {
		if (StringUtils.isBlank(paramsJSON)) {
			setParamsJSON(JSON.toJSONString(params));
		}
		this.params = params;
	}

	public String getParamsJSON() {
		if (StringUtils.isBlank(paramsJSON) && getParams() != null) {
			setParamsJSON(JSON.toJSONString(getParams()));
		}
		return paramsJSON;
	}

	public void setParamsJSON(String paramsJSON) {
		this.paramsJSON = paramsJSON;
	}

	public Date getPastDueDate() {
		return pastDueDate;
	}

	public void setPastDueDate(Date pastDueDate) {
		this.pastDueDate = pastDueDate;
	}

}
