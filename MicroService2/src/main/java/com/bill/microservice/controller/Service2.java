package com.bill.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.service.Service2DbConnectImpl;
import com.bill.microservice.service.Service2FeignCallImpl;
import com.bill.microservice.service2.Service2DbConnectDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service2")
public class Service2 {
	
	@Autowired
	Service2FeignCallImpl service2FeignCallImpl;
	
	@Autowired
	Service2DbConnectImpl service2DbConnectImpl;
	
	//1. 透過service1 feign 呼叫次 api
	@PostMapping(value = "/feign/call")
	public BaseDtoRes callByFeign(@RequestBody @Valid BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service2FeignCallImpl.process(gatewayReq);
	}
	
	//2. 連線 Datasouce 至 DB1 與 DB2
	@PostMapping(value = "/db/connect")
	public BaseDtoRes connectDB(@RequestBody @Valid BaseWebGatewayReq<Service2DbConnectDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service2DbConnectImpl.process(gatewayReq);
	}
}
