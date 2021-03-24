package com.bill.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.bill.microservice.common.logging.LoggingInterceptor;

@Component
public class InterceptorConfig extends WebMvcConfigurationSupport{

	@Autowired
	LoggingInterceptor loggingInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true).defaultContentType(MediaType.APPLICATION_JSON);
	}
}
