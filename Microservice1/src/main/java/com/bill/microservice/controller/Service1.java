package com.bill.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.service.Service1ExceptionThrowImpl;
import com.bill.microservice.service.Service1ReqResWrapImpl;
import com.bill.microservice.service.Service1Service2CallImpl;
import com.bill.microservice.service.Service1UtilCallImpl;
import com.bill.microservice.service1.Service1ExceptionThrowDtoReq;
import com.bill.microservice.service1.Service1ReqResWrapDtoReq;
import com.bill.microservice.service1.Service1Service2CallDtoReq;
import com.bill.microservice.service1.Service1UtilCallDtoReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service1")
public class Service1 {
		
	@Autowired
	private Service1UtilCallImpl service1UtilCallImpl;
	
	@Autowired
	private Service1ReqResWrapImpl service1ReqResWrapImpl;

	@Autowired
	private Service1ExceptionThrowImpl service1ExceptionThrowImpl;
	
	@Autowired
	private Service1Service2CallImpl service1Service2CallImpl;
	
	
	//1. 呼叫"MicroUtil method"
	@PostMapping(value = "/util/call")
	public BaseDtoRes callTestUtil(@RequestBody @Valid BaseWebGatewayReq<Service1UtilCallDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service1UtilCallImpl.process(gatewayReq);
	}
	
	//2. req,res封裝統一格式
	@PostMapping(value = "/reqres/wrap")
	public BaseDtoRes testService1(@RequestBody @Valid BaseWebGatewayReq<Service1ReqResWrapDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service1ReqResWrapImpl.process(gatewayReq);
	}
	
	//3. 例外處理
	@PostMapping(value = "/exception/throw")
	public void throwException(@RequestBody @Valid BaseWebGatewayReq<Service1ExceptionThrowDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		service1ExceptionThrowImpl.process(gatewayReq);
	}
	
	//4. 透過 feign 調用 Service2
	@PostMapping(value = "/service2/call")
	public BaseDtoRes callService2(@RequestBody @Valid BaseWebGatewayReq<Service1Service2CallDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service1Service2CallImpl.process(gatewayReq);
	}
}
