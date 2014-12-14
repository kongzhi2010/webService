package husd.wsi.back.service;

import org.springframework.stereotype.Service;

@Service("retryDemo")
public class RetryDemo {
	int i=0;
	public void startDemo(){
		System.out.println(i++);
	}

}
