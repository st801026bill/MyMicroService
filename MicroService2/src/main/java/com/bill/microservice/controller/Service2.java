package com.bill.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.service.Service2FeignCallImpl;
import com.bill.microservice.service2.Service2FeignCallDtoReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service2")
public class Service2 {
	
	@Autowired
	Service2FeignCallImpl service2FeignCallImpl;
	
	//1. 透過 feign 呼叫 MicroService2
	@PostMapping(value = "/feign/call")
	public BaseDtoRes callByFeign(@RequestBody @Valid BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		
		return service2FeignCallImpl.process(gatewayReq);
	}
}
