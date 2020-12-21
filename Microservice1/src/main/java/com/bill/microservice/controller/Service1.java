package com.bill.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.common.exception.ErrorType;
import com.bill.microservice.common.exception.ModuleException;
import com.bill.microservice.service.Service1TestImpl;
import com.bill.microservice.service1.Service1TestDtoReq;
import com.bill.microservice.util.TestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service1")
public class Service1 {
	
	@Autowired
	Service1TestImpl service1TestImpl;
	
	@Autowired
	TestUtil testUtil;
	
	@PostMapping(value = "/method1")
	public String method1() {
		return "local method1";
	}
	
	@PostMapping(value = "/method2")
	public String callTestUtil() {
		return testUtil.callTestUtil();
	}
	
	@PostMapping(value = "/test")
	public BaseDtoRes testService1(@RequestBody @Valid BaseWebGatewayReq<Service1TestDtoReq> gatewayReq) {
		log.info("Got Request Body:{}", gatewayReq);
		return service1TestImpl.process(gatewayReq);
	}
	
	@PostMapping(value = "/exception/throw")
	public void throwException() {
		if(true) throw new ModuleException(ErrorType.DATA_NOT_FOUND_ERROR);
		return;
	}
	
}
