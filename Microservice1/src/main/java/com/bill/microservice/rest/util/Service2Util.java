package com.bill.microservice.rest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.rest.client.InvokeService2FeignCallRestClientUtil;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;

@Service
public class Service2Util {
	@Autowired
	InvokeService2FeignCallRestClientUtil invokeService2FeignCallRestClientUtil;
	
//	public BaseWebGatewayRes<Service2FeignCallDtoRes> getStudentDetail(String id, String name) {
//		return this.invokeService2FeignCallService(id, name);
//	}
//	
//	private BaseWebGatewayRes<Service2FeignCallDtoRes> invokeService2FeignCallService(String studentId ,String studentName) {
//		Service2FeignCallDtoReq req = Service2FeignCallDtoReq.builder()
//				.studentId(studentId)
//				.studentName(studentName)
//				.build();
//		return invokeService2FeignCallRestClientUtil.sendRestClientRequest(req);
//	}
	
	public Service2FeignCallDtoRes getStudentDetail(String id, String name) {
		return this.invokeService2FeignCallService(id, name);
	}
	
	private Service2FeignCallDtoRes invokeService2FeignCallService(String studentId ,String studentName) {
		Service2FeignCallDtoReq req = Service2FeignCallDtoReq.builder()
				.studentId(studentId)
				.studentName(studentName)
				.build();
		return invokeService2FeignCallRestClientUtil.sendRestClientRequest(req);
	}
}
