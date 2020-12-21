package com.bill.microservice;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.base.BaseWebGatewayRes.BaseWebGatewayMWHeaderRes;
import com.bill.microservice.common.exception.ErrorType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class MicroGatewayApplication {

	@Autowired
	ObjectMapper mapper;
	
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
				.filters(f -> f.modifyRequestBody(String.class, Object.class, MediaType.APPLICATION_JSON_VALUE,
						(exchange, s) -> {
							BaseWebGatewayReq req = null;
							try {
								req = mapper.readValue(s, BaseWebGatewayReq.class);
								return Mono.just(req);
							} catch (Exception e) {
								// TODO: handle exception
								log.error(e.getMessage());
								return null;
							} finally {
								log.info("==== geteway request URI: {}, ID: {}, PATH: {}, request: {}", uri, id, path, req);
							}
						})
				.modifyResponseBody(String.class, Object.class, MediaType.APPLICATION_JSON_VALUE,
						(exchange, s) -> {
							BaseWebGatewayRes<HashMap<String, Object>> res = null;
							try {
								HashMap<String, Object> tranrs = mapper.readValue(s, HashMap.class);
								String txnseq = UUID.randomUUID().toString();
								
								/*
								 * 	正常情況會回BaseDtoRes，必須再塞入BaseWebGateWayRes才能輸出
								 * 	但ExceptionHandler會丟回完整的BaseWebGateWayRes，直接輸出即可
								 */
								
								res = tranrs.containsKey("MWHEADER") && tranrs.containsKey("TRANRS")?
										mapper.convertValue(tranrs, BaseWebGatewayRes.class):
										new BaseWebGatewayRes<>(new BaseWebGatewayMWHeaderRes(txnseq, ErrorType.SUCCESS.getCode(), ErrorType.SUCCESS.getMessage()), tranrs);
								res.getMwheader().setTxnseq(txnseq);
								
								return Mono.just(res);
							} catch (JsonProcessingException e) {
								// TODO Auto-generated catch block
								log.error(e.getMessage());
								return null;
							} finally {
								log.info("==== geteway response URI: {}, ID: {}, PATH: {}, request: {}", uri, id, path, res);
							}
						})
				)
				.uri(uri));
	}

}
