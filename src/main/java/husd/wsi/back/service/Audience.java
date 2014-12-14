package husd.wsi.back.service;

import org.springframework.stereotype.Service;

@Service
public class Audience {
	
	public void takeSeats() {
		System.out.println("take seat!");
	}

	public void turnOffCellPhone() {
		System.out.println("turn off your cellPhone!");
	}

	public void standUp() {
		System.out.println("standUp!");
	}
}
