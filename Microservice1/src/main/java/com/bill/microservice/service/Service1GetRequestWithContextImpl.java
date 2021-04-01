package com.bill.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.common.config.RequestScopeContext;
import com.bill.microservice.service1.Service1GetRequestByContextDtoReq;
import com.bill.microservice.service1.Service1GetRequestByContextDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1GetRequestWithContextImpl implements IBaseService<Service1GetRequestByContextDtoRes>{
	
	@Autowired
	RequestScopeContext requestScopeContext;
	
	@Override
	public Service1GetRequestByContextDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		Service1GetRequestByContextDtoReq request = (Service1GetRequestByContextDtoReq) gatewayReq.getTranrq();
		log.info("StudentDtoReq: {}", request);
		
		log.info("==== requestScopeContext ====");
		log.info(requestScopeContext.getBaseWebGatewayReq().toString());
		
		return new Service1GetRequestByContextDtoRes();
	}
	
}
