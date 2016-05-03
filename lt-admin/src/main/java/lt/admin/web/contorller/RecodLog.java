package lt.admin.web.contorller;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;  
  


import lt.admin.dto.AdminDTO;
import lt.utils.SessionUtil;

import org.apache.log4j.MDC;  
  
  
public class RecodLog implements Filter{  
  
       
    private final static double DEFAULT_USERID= Math.random()*100000.0;    
  
    public void destroy() {  
    }  
  
    public void doFilter(HttpServletRequest request, ServletResponse response,  
           FilterChain chain) throws IOException, ServletException {  
       HttpServletRequest req=(HttpServletRequest)request;  
        HttpSession session= req.getSession();  
        if (session==null){  
            MDC.put("userId",DEFAULT_USERID);    
        }  
        else{  
        	
        	AdminDTO dto=SessionUtil.getLoginAdmin(request);
            if (dto==null){  
                MDC.put("userId",DEFAULT_USERID);  
                MDC.put("userName",DEFAULT_USERID);  
            }  
            else  
            {  
                MDC.put("userId",dto.getId());  
                MDC.put("userName",dto.getName());  
            }  
        }  
        //logger.info("test for MDC.");  
  
       chain.doFilter(request, response);  
    }  
    public void init(FilterConfig Config) throws ServletException {  
  
    }

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
	}  
} 
