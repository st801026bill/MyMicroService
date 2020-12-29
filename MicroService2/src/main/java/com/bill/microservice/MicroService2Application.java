package com.bill.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroService2Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroService2Application.class, args);
	}

}
