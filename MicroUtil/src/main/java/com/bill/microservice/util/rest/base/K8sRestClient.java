package com.bill.microservice.util.rest.base;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.common.enums.ServiceMappingEnum;
import com.bill.microservice.util.config.LocalIpConfig;

@Profile("!default")
@Component
public class K8sRestClient extends RestClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LocalIpConfig config;
	
	@Override
	protected String buildRequestUrl(ServiceMappingEnum mapping) {
		return String.format("http://%s:%s%s", config.getIp(), config.getPort(), mapping.getUri());
	}

//	@Override
//	public BaseWebGatewayRes<HashMap<String, Object>> postRequest(BaseWebGatewayReq<? extends BaseDtoReq> gatewatReq,
//			ServiceMappingEnum mapping) {
//		String requestUrl = buildRequestUrl(mapping);
//		ResponseEntity<BaseWebGatewayRes<HashMap<String, Object>>> exchange = restTemplate.exchange(requestUrl, HttpMethod.POST, buildRequestEntity(gatewatReq), new ParameterizedTypeReference<BaseWebGatewayRes<HashMap<String, Object>>>() {});
//		return exchange.getBody();
//	}
	
	@Override
	public HashMap<String, Object> postRequest(BaseWebGatewayReq<? extends BaseDtoReq> gatewatReq,
			ServiceMappingEnum mapping) {
		String requestUrl = buildRequestUrl(mapping);
		ResponseEntity<HashMap<String, Object>> exchange = restTemplate.exchange(requestUrl, HttpMethod.POST, buildRequestEntity(gatewatReq), new ParameterizedTypeReference<HashMap<String, Object>>() {});
		return exchange.getBody();
	}
}
