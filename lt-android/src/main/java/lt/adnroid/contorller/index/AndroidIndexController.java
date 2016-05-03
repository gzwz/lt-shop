package lt.adnroid.contorller.index;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AndroidIndexController {
	
	@RequestMapping(value = "android/index")
	private String index(HttpServletRequest request,Model model)  {
		System.out.println("进入首页");
		return "/index.html";
	}
}
