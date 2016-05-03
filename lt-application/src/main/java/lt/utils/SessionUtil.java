package lt.utils;


import javax.servlet.http.HttpServletRequest;

import lt.admin.dto.AdminDTO;
import lt.user.dto.UserDTO;

/**
 * session操作工具类
 * @author chenhaohao
 *
 */
public class SessionUtil {
	/** 前台登陆用户*/
	public static final String LOGIN_USER = "login_user";
	
	/** 令牌*/
	public static final String ENTRYID = "initEntryId";
	
	/** 微信用户openid*/
	public static final String SESSION_OPENID = "open_id";
	
	/** 后台登陆管理员*/
	public static final String LOGIN_ADMIN = "login_admin";
	
	public static final String KAPTCHA_SESSION_KEY = "kaptcha_key";
	
	/**
	 * 将令牌存入session
	 * @param request
	 * @param user
	 */
	public static void putEntryId(HttpServletRequest request, String entryId){
		putSession(request, ENTRYID, entryId);
	}
	
	/**
	 * 获取令牌session
	 * @param request
	 * @param user
	 * @return 
	 */
	public static String getEntryId(HttpServletRequest request){
		return (String) getStr(request, ENTRYID);
	}
	
	/**
	 * 获取登陆管理员
	 * @param request
	 * @return
	 */
	public static AdminDTO getLoginAdmin(HttpServletRequest request){
		return (AdminDTO) getObj(request, LOGIN_ADMIN);
	}
	
	/**
	 * 将登陆管理员存入session
	 * @param request
	 * @param user
	 */
	public static void putLoginAdmin(HttpServletRequest request, AdminDTO admin){
		putSession(request, LOGIN_ADMIN, admin);
	}
	
	/**
	 * 获取登陆会员
	 * @param request
	 * @return
	 */
	public static UserDTO getLoginUser(HttpServletRequest request){
		return (UserDTO) getObj(request, LOGIN_USER);
	}
	/**
	 * 将登陆普通会员存入session
	 * @param request
	 * @param user
	 */
	public static void putLoginUser(HttpServletRequest request, UserDTO user){
		putSession(request, LOGIN_USER, user);
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取微信用户openid
	 * @param request
	 * @return
	 */
	public static String getOpenId(HttpServletRequest request){
		return getStr(request, SESSION_OPENID);
	}
	/**
	 * 将微信用户保存到session
	 * @param request
	 * @param openid
	 */
	public static void putOpenId(HttpServletRequest request, String openid){
		putSession(request, SESSION_OPENID, openid);
	}
	

	public static void putSession(HttpServletRequest request, String attrName, Object obj){
		request.getSession().setAttribute(attrName, obj);
	}
	/** 移除后台 普通用户 session*/
	public static void removeUserSession(HttpServletRequest request){
		request.getSession().removeAttribute("login_user");
	}
	
	
	/** 移除后台 管理员 session*/
	public static void removeSession(HttpServletRequest request){
		request.getSession().removeAttribute("login_admin");
	}
	
	public static String getStr(HttpServletRequest request, String attrName){
		return (String) request.getSession().getAttribute(attrName);
	}
	
	public static Integer getInt(HttpServletRequest request, String attrName){
		return (Integer) request.getSession().getAttribute(attrName);
	}
	
	public static Long getLong(HttpServletRequest request, String attrName){
		return (Long) request.getSession().getAttribute(attrName);
	}
	
	public static Double getDouble(HttpServletRequest request, String attrName){
		return (Double) request.getSession().getAttribute(attrName);
	}
	
	public static Float getFloat(HttpServletRequest request, String attrName){
		return (Float) request.getSession().getAttribute(attrName);
	}
	
	public static Object getObj(HttpServletRequest request, String attrName){
		return request.getSession().getAttribute(attrName);
	}
	
}
