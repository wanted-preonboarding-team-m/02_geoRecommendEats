package com.wanted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GeoRecommendEatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoRecommendEatsApplication.class, args);
	}

}
