package com.bill.microservice.util.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "local")
@Component
public class LocalIpConfig {
	private String ip;
	private String port;
}
