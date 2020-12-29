package com.bill.microservice.service;

import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.service1.Service1ReqResWrapDtoReq;
import com.bill.microservice.service1.Service1ReqResWrapDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1ReqResWrapImpl implements IBaseService<Service1ReqResWrapDtoRes>{
	
	@Override
	public Service1ReqResWrapDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1ReqResWrapDtoReq request = (Service1ReqResWrapDtoReq) gatewayReq.getTranrq();
		log.info("StudentDtoReq: {}", request);
		
		return Service1ReqResWrapDtoRes.builder()
				.studentId(request.getStudentId())
				.studentName(request.getStudentName())
				.comment(request.getStudentId() +" "+ request.getStudentName()).build();
	}
	
}
