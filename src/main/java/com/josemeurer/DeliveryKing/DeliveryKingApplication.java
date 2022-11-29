package com.josemeurer.DeliveryKing;

import com.josemeurer.DeliveryKing.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryKingApplication {

	@Autowired
	private PhoneService phoneService;

	public static void main(String[] args) {
		SpringApplication.run(DeliveryKingApplication.class, args);
	}

}
