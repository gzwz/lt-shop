package lt.pc.web.contorller.interceptor;
import gzlazypack.common.util.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局拦截器
 * 
 * @author hao_chenhh
 * 
 */
public class CommonInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		
		String entryId=request.getParameter("entryId");
		
		//跳到详情页
		if(StringUtils.isNotBlank(entryId)){
			session.setAttribute("marketingTokenId", entryId);
			response.sendRedirect((request.getContextPath() + "/index"));
		}
				

		session.setAttribute("imageContextPath", PropertiesUtil
				.getProperiesValue("imageContextPath", "/system.properties"));
		return true;
	}
	
	
	
	
	
	

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
