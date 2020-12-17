package com.bill.microservice.service;

import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.service1.Service1TestDtoReq;
import com.bill.microservice.service1.Service1TestDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1TestImpl implements IBaseService<Service1TestDtoRes>{
	
	@Override
	public Service1TestDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1TestDtoReq request = (Service1TestDtoReq) gatewayReq.getTranrq();
		log.info("StudentDtoReq: {}", request);
		
		return Service1TestDtoRes.builder()
				.studentId(request.getStudentId())
				.studentName(request.getStudentName())
				.comment(request.getStudentId() +" "+ request.getStudentName()).build();
	}
	
}
