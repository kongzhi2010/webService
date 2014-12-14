package husd.wsi.front.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	public static final int DEFAULT_PER_PAGE = 26;
	
	/***
	 *@RequestMapping ({"/","/home"}) ��ʾ����/����/home������
	 * 
	 */
	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String, Object> model){
		model.put("demo", "this is a demo");
		return "home";
	}
}
