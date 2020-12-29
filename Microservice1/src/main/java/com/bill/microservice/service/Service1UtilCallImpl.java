package com.bill.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.service1.Service1UtilCallDtoReq;
import com.bill.microservice.service1.Service1UtilCallDtoRes;
import com.bill.microservice.util.TestUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1UtilCallImpl implements IBaseService<Service1UtilCallDtoRes>{
	
	@Autowired
	TestUtil testUtil;
	
	@Override
	public Service1UtilCallDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1UtilCallDtoReq request = (Service1UtilCallDtoReq) gatewayReq.getTranrq();
		log.info("Service1UtilCallDtoReq: {}", request);
		
		return Service1UtilCallDtoRes.builder()
				.message(testUtil.callTestUtil()).build();
	}
	
}
