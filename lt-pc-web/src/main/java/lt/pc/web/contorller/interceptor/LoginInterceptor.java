package lt.pc.web.contorller.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.user.dto.UserDTO;
import lt.utils.SessionUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 登陆拦截器
 * 
 * @author 
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		
		UserDTO user = SessionUtil.getLoginUser(request);
		// 如果没有登录，则跳转到登录页面
		if (user == null) {
			String redirect_url = request.getHeader("Referer");
			if (getRightUrl(redirect_url)) {
				request.getSession().removeAttribute("redirect_url");
				redirect_url ="";
			}
			request.getSession().setAttribute("redirect_url", redirect_url);
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 判断是否拦截到是链接是退出 或者是注册，是 则返回 true
	 * @return
	 */
	private boolean getRightUrl(String url) {
		if (url == null || url =="") {
			return false;
		}
		String[] b = url.split("/");
		if ((b[b.length-2].equals("login")&&b[b.length-1].equals("logout"))) {
			return true;
		}
		if (b[b.length-1].equals("register")) {
			return true;
		}
		
		return false;
		
	}

}
