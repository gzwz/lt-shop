package lt.base.event;
public class MsgTemplate_w {

	/**
	 * 普通用户-注册类验证
	 */
	public static final String RegisterMsgTemplate = "您正在通过卡车网注册认证，验证码为%s 打死也不告诉别人。详情登陆www.e-kcw.com";
	
	/**
	 * 变更密码
	 */
	public static final String PassWordChangeMsgTemplate = "您正在通过卡车网进行密码变更申请，验证码为%s 打死也不告诉别人。详情登陆www.e-kcw.com";
	
	/**
	 * 车辆信息绑定
	 */
	public static final String CarInfoBindMsgTemplate = "您正在通过卡车网申请车牌%s车辆与司机%s绑定，验证码为%s 打死也不告诉别人。详情登陆www.e-kcw.com";
	
	
	/**
	 * 车辆信息解绑
	 */
	public static final String CarInfoUnBindMsgTemplate = "您正在通过卡车网申请车牌%s车解绑，验证码为%s 打死也不告诉别人。详情登陆www.e-kcw.com";
	
	
	/**
	 * 在线支付
	 */
	public static final String OnlinePayMsgTemplate = "您正在通过卡车宝进行交易，验证码为%s，打死也不告诉别人。详情登陆www.e-kcw.com";
	
	/**
	 * 运单签收验证码
	 */
	public static final String SignMsgTemplate = "您好！您的货物已安全送达，签收验证码为%s，打死也不告诉别人。详情登陆www.e-kcw.com";
	
	/**
	 * 司机认证审核
	 */
	public static final String DriverAuditMsgTemplate = "您提交的司机认证,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 司机认证审核
	 */
	public static final String DriverEnterpriseAuditMsgTemplate = "您提交的司机认证企业认证,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 货主认证审核 
	 */
	public static final String SupplyAuditMsgTemplate = "您提交的货主认证,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 车主认证审核 
	 */
	public static final String CarOwnerAuditMsgTemplate = "您提交的车主认证,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 车主认证审核 -企业审核
	 */
	public static final String CarOwnerEnterpriseAuditMsgTemplate = "您提交的企业车主认证,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 审核车辆认证 
	 */
	public static final String CarAuditMsgTemplate = "您的车辆%s,%s。详情登陆www.e-kcw.com";
	
	/**
	 *  修改车辆认证锁状态 
	 */
	public static final String VehicleAuditVisibleMsgTemplate = "%s,车牌号%s,。详情登陆www.e-kcw.com";
	
	/**
	 * 货主认证--企业认证
	 */
	public static final String DisableSupplyEnterpriseAuditMsgTemplate = "您的货主权限,%s。详情登陆www.e-kcw.com";
	
	/**
	 * 货主认证审核
	 */
	public static final String auditSupplyEnterpriseAuditMsgTemplate = "您的货主企业认证,%s。详情登陆www.e-kcw.com";
	
	
	
	public static String GetMsgTemplateContent(Short type) {

		switch (type) {
		case 1:
			return RegisterMsgTemplate;
		case 2:
			return PassWordChangeMsgTemplate;
			
		case 3:
			return CarInfoBindMsgTemplate;
		case 4:
			return CarInfoUnBindMsgTemplate;
			
		case 5:
			return OnlinePayMsgTemplate;
		case 6:
			return null;
		case 7:
			return null;
		case 8:
			return null;
		case 9:
			return SignMsgTemplate;
		case 10:
			return DriverAuditMsgTemplate;
			
		default:
			return null;

		}
		
	}

}