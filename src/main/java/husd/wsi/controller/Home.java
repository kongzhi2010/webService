package husd.wsi.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@RequestMapping({"/","/home"})
	public String showHomePage(Map<String, Object> model){
		model.put("demo", "this is a demo");
		return "home";
	}
}
