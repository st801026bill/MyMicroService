package com.bill.microservice.service;

import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service2FeignCallImpl implements IBaseService<Service2FeignCallDtoRes>{
	
	@Override
	public Service2FeignCallDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service2FeignCallDtoReq request = (Service2FeignCallDtoReq) gatewayReq.getTranrq();
		log.info("Service2FeignCallDtoReq: {}", request);
		
		String message = String.format("Hello!，No: %s, %s", request.getStudentId(), request.getStudentName());
		return Service2FeignCallDtoRes.builder()
				.message(message).build();
	}
	
}
