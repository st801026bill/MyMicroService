package com.bill.microservice.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.common.config.RequestScopeContext;
import com.bill.microservice.common.exception.ModuleException;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;
import com.bill.microservice.util.rest.Service2FeignCallRestClient;

@Service
public class InvokeService2FeignCallRestClientUtil extends BaseRestUtil<Service2FeignCallDtoReq, Service2FeignCallDtoRes> {

	@Autowired
	private RequestScopeContext requestScopeContext;
	
	@Autowired
	private Service2FeignCallRestClient service2Client; 
	
	@Override
	protected BaseWebGatewayReq<Service2FeignCallDtoReq> buildRestRequest(Service2FeignCallDtoReq baseDtoReq) throws ModuleException {
		BaseWebGatewayReq<Service2FeignCallDtoReq> restClientRequest = new BaseWebGatewayReq<>();
		restClientRequest.setMwheader(requestScopeContext.getBaseWebGatewayReq().getMwheader());
		restClientRequest.setTranrq(baseDtoReq);
		return restClientRequest;
	}

//	@Override
//	protected BaseWebGatewayRes<Service2FeignCallDtoRes> invokeRestClient(BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
//		return service2Client.invokeService2FeignCall(gatewayReq);
//	}
	
	@Override
	protected Service2FeignCallDtoRes invokeRestClient(BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
		return service2Client.invokeService2FeignCall(gatewayReq);
	}
	
}
