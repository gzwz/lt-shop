package lt.admin.web.contorller.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lt.admin.dto.AdminDTO;
import lt.utils.SessionUtil;

/**
 * 登陆拦截器
 * 
 * @author wxp
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if (StringUtils.equals(url, request.getContextPath() + "/login") 
				|| StringUtils.equals(url, request.getContextPath() + "/submit/login")
				|| url.indexOf("resources") != -1) {
			return true;
		}
		AdminDTO loginAdmin = SessionUtil.getLoginAdmin(request);
		if (loginAdmin == null) {
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

}
