package com.example.addDistanceClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AddDistanceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddDistanceClientApplication.class, args);
	}

}
