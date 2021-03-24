package com.bill.microservice.service;

import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.common.exception.ErrorType;
import com.bill.microservice.common.exception.ModuleException;
import com.bill.microservice.service1.Service1ExceptionThrowDtoReq;
import com.bill.microservice.service1.Service1ReqResWrapDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1ExceptionThrowImpl implements IBaseService<Service1ReqResWrapDtoRes>{
	
	@Override
	public Service1ReqResWrapDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1ExceptionThrowDtoReq request = (Service1ExceptionThrowDtoReq) gatewayReq.getTranrq();
		log.info("StudentDtoReq: {}", request);
		
		if(true) throw new ModuleException(ErrorType.UNKNOW_ERROR);
		
		return null;
	}
	
}
