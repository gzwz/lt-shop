package lt.adnroid.contorller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author chenhaohao
 *
 */
public class BaseController {

	/**
	 * 直接从response输出内容（通常用作ajax返回）
	 */
	protected boolean print(HttpServletResponse response, String msg) {
		try {
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			response.getWriter().print(msg);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
