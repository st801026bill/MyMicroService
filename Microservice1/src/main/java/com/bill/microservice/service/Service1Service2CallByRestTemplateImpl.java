package com.bill.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.rest.util.Service2Util;
import com.bill.microservice.service1.Service1Service2CallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1Service2CallByRestTemplateImpl implements IBaseService<BaseDtoRes>{
	
	@Autowired
	private Service2Util service2Util;
	
	@Override
	public BaseDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1Service2CallDtoReq request = (Service1Service2CallDtoReq) gatewayReq.getTranrq();
		log.info("Service1Service2CallDtoReq: {}", request);
		Service2FeignCallDtoRes res = service2Util.getStudentDetail(request.getStudentId(), request.getStudentName());
		log.info(res.toString());
		return res;
	}	
}
