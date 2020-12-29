package com.bill.microservice.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.service1.Service1UtilCallDtoReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service2")
public class Service2 {
	//2. 呼叫"MicroUtil method"
	@PostMapping(value = "/feign/call")
	public BaseDtoRes callTestUtil(@RequestBody @Valid BaseWebGatewayReq<Service1UtilCallDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return null;
	}
}
