package com.bill.microservice.feign.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;

@FeignClient(name="gateway", contextId="service2Client")
public interface Service2Client {
	@PostMapping(path = "/service2/feign/call")
	BaseWebGatewayRes<Service2FeignCallDtoRes> invokeService2FeignCall(@RequestBody @Valid BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq);
}
