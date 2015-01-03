package husd.wsi.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RetryDemo {

	private Logger logger = Logger.getLogger(RetryDemo.class);

	int i = 0;

	public void startDemo() {
		logger.info("i is "+i);
		i++;
	}

}
