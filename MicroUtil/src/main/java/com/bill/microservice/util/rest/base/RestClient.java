package com.bill.microservice.util.rest.base;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.common.enums.ServiceMappingEnum;

public abstract class RestClient {
	
	//1. 取得微服務連線資訊
	protected abstract String buildRequestUrl(ServiceMappingEnum mapping);
	
	//2. 發送https
//	public abstract BaseWebGatewayRes<HashMap<String, Object>> postRequest(BaseWebGatewayReq<? extends BaseDtoReq> gatewatReq, ServiceMappingEnum mapping);
	public abstract HashMap<String, Object> postRequest(BaseWebGatewayReq<? extends BaseDtoReq> gatewatReq, ServiceMappingEnum mapping);
	
	//3. 打包HttpHeaders
	public HttpEntity<BaseWebGatewayReq<? extends BaseDtoReq>> buildRequestEntity(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return new HttpEntity<BaseWebGatewayReq<? extends BaseDtoReq>>(gatewayReq, httpHeaders);
	}
}
