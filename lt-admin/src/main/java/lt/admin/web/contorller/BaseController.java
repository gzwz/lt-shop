package lt.admin.web.contorller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




import lt.product.entity.ProductParameter;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 控制器基类
 * 
 * @author chenhaohao
 * 
 */
public class BaseController {

	


	/**
	 * 自动将yyyy-MM-dd格式的参数转换成Date型参数
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormat1.setLenient(true);
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat1, true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat2, true));

	}
	
	//去重
		public List<?> psiqList(List<?> psiList){
			for(int i = 0; i<psiList.size(); i++){
				for(int j = psiList.size()-1; j>i; j--){
					if(psiList.get(j).equals(psiList.get(i))){
						psiList.remove(j); 
					}
				}
			}
			return psiList;
		}

}
