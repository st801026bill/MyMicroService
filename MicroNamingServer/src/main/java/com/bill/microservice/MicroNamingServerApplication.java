package com.bill.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroNamingServerApplication.class, args);
	}

}
