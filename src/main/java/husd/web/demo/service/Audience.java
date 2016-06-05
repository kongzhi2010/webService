package husd.web.demo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Audience {

	private Logger logger = Logger.getLogger(Audience.class);

	public void takeSeats() {
		logger.info("takeSeats");
	}

	public void turnOffCellPhone() {
		logger.info("turnoff all cell phone");
	}

	public void standUp() {
		logger.info("stand up");
	}
}
