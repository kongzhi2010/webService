package husd.wsi.back.service;

import org.springframework.stereotype.Service;


@Service("demo")
public class Demo {
	
	public String onMessage(String requestXml){
		System.out.println(requestXml);
		return "get it!";
	}

	public Object getRecentPage(int defaultPerPage) {
		return "this is a demo";
	}
	
}
