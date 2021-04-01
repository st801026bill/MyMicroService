package com.bill.microservice.util.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.common.enums.ServiceMappingEnum;
import com.bill.microservice.service2.Service2FeignCallDtoReq;
import com.bill.microservice.service2.Service2FeignCallDtoRes;
import com.bill.microservice.util.rest.base.IRestClient;
import com.bill.microservice.util.rest.base.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Service2FeignCallRestClient implements IRestClient<Service2FeignCallDtoRes> {
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private RestClient restClient;
	
//	public BaseWebGatewayRes<Service2FeignCallDtoRes> invokeService2FeignCall(BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
//		BaseWebGatewayRes<HashMap<String, Object>> response = restClient.postRequest(gatewayReq, ServiceMappingEnum.SERVICE2_FEIGN_CALL);
//		return getResponse(response);
//	}
	
//	@Override
//	public BaseWebGatewayRes<Service2FeignCallDtoRes> getResponse(BaseWebGatewayRes<HashMap<String, Object>> gatewayRes) {
//		Service2FeignCallDtoRes tranrs = mapper.convertValue(gatewayRes.getTranrs(), Service2FeignCallDtoRes.class);
//		BaseWebGatewayRes<Service2FeignCallDtoRes> res = new BaseWebGatewayRes<Service2FeignCallDtoRes>();
//		res.setMwheader(gatewayRes.getMwheader());
//		res.setTranrs(tranrs);
//		return res;
//	}
	
	public Service2FeignCallDtoRes invokeService2FeignCall(BaseWebGatewayReq<Service2FeignCallDtoReq> gatewayReq) {
		HashMap<String, Object> response = restClient.postRequest(gatewayReq, ServiceMappingEnum.SERVICE2_FEIGN_CALL);
		return getResponse(response);
	}
	
	@Override
	public Service2FeignCallDtoRes getResponse(HashMap<String, Object> gatewayRes) {
		Service2FeignCallDtoRes tranrs = mapper.convertValue(gatewayRes, Service2FeignCallDtoRes.class);
//		BaseWebGatewayRes<Service2FeignCallDtoRes> res = new BaseWebGatewayRes<Service2FeignCallDtoRes>();
//		res.setMwheader(gatewayRes.getMwheader());
//		res.setTranrs(tranrs);
		return tranrs;
	}
}
