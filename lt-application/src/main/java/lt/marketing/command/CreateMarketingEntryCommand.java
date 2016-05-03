package lt.marketing.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 创建推广链接
 * 
 * 
 */
@SuppressWarnings("serial")
public class CreateMarketingEntryCommand extends BaseCommand {

	/**
	 * 推广主体id，常见的是用户id
	 */
	private String subjectId;

	/**
	 * 推广主体type，常见的是"user"
	 */
	private String subjectType;

	/**
	 * 推广渠道类型
	 */
	private String channelType;

	public final static String CHANNEL_TYPE_WB = "wb"; // 新浪微博
	public final static String CHANNEL_TYPE_QQ = "qq"; // QQ
	public final static String CHANNEL_TYPE_WX = "wx"; // 微信
	public final static String CHANNEL_TYPE_BD = "bd"; // 百度

	/**
	 * 被推广的目标类型
	 */
	private String targetType;

	public final static String TARGET_TYPE_ME = "me"; // 自己，纯发展下线，入口进来就是注册页
	public final static String TARGET_TYPE_CATEGORY = "category"; // 某个品类，入口进来是该品类商品列表页
	public final static String TARGET_TYPE_PRODUCT = "product"; // 商品，入口进来是商品详情页
	public final static String TARGET_TYPE_WEBSITE = "website"; // 整站，入口进来是商城首页

	/**
	 * 被推广的目标实体 type为me时为userId，type为category时为productCategoryId信息，
	 * type为product时为productId信息，type为website时为空
	 */
	private String targetId;

	/**
	 * 推广目标名称
	 */
	private String targetName;

	/**
	 * 目标URL
	 */
	private String targetUrl;

	/**
	 * 简介
	 */
	private String intro;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

}
