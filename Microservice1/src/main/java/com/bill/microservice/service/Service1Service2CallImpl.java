package com.bill.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayReq.BaseWebGatewayMWHeaderReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.feign.client.Service2Client;
import com.bill.microservice.service1.Service1Service2CallDtoReq;
import com.bill.microservice.service1.Service1Service2CallDtoRes;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service1Service2CallImpl implements IBaseService<Service1Service2CallDtoRes>{
	
	@Autowired
	private Service2Client service2Client;
	
	@Override
	public Service1Service2CallDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service1Service2CallDtoReq request = (Service1Service2CallDtoReq) gatewayReq.getTranrq();
		log.info("Service1Service2CallDtoReq: {}", request);
		
		BaseWebGatewayRes<Service2FeignCallDtoRes> service2FeignCallRes = invokeService2FeignCall(request.getStudentId(), request.getStudentName());
		String message = String.format("(service1 invoke by feign) %s", service2FeignCallRes.getTranrs().getMessage());
		
		return Service1Service2CallDtoRes.builder()
				.message(message).build();
	}
	
	private BaseWebGatewayRes<Service2FeignCallDtoRes> invokeService2FeignCall(String id, String name) {
		BaseWebGatewayReq<Service2FeignCallDtoReq> baseWebGatewayReq = new BaseWebGatewayReq<>();
		
		Service2FeignCallDtoReq service2FeignCallDtoReq = new Service2FeignCallDtoReq(id, name);
		baseWebGatewayReq.setMwheader(new BaseWebGatewayMWHeaderReq(""));
		baseWebGatewayReq.setTranrq(service2FeignCallDtoReq);
		return service2Client.invokeService2FeignCall(baseWebGatewayReq);
	}
	
}
