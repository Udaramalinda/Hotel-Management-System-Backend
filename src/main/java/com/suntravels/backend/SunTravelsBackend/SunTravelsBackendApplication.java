package com.suntravels.backend.SunTravelsBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.suntravels.backend.SunTravelsBackend")
@EntityScan("com.suntravels.backend.SunTravelsBackend.model")
public class SunTravelsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunTravelsBackendApplication.class, args);
	}

}
