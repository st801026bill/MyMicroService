package com.bill.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		Builder routes = builder.routes();
		
		routes = createRoute(routes, "lb://MICROSERVICE1", "service1_route", "/service1/**");
		
		return routes.build();
	}
	
	private Builder createRoute(Builder routes, String uri, String id, String path) {
		return routes.route(id, r -> r.path(path)
//				.filters(f -> f.modifyRequestBody(String.class, Object.class, MediaType.APPLICATION_JSON_VALUE,
//						(exchange, s) -> {
//							return null;
//						})
//				.modifyResponseBody(String.class, Object.class, MediaType.APPLICATION_JSON_VALUE,
//						(exchange, s) -> {
//							return null;
//						})
//				)
				.uri(uri));
	}

}
