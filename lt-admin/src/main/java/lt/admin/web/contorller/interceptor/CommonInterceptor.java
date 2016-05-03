package lt.admin.web.contorller.interceptor;

import gzlazypack.common.util.PropertiesUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局拦截器
 * 
 * @author wo
 * 
 */
public class CommonInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
	    
		HttpSession session = request.getSession();
		session.setAttribute("imageContextPath", PropertiesUtil
				.getProperiesValue("imageContextPath", "/system.properties"));
		
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

}
