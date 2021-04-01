package com.bill.microservice.util.rest.base;

import java.util.HashMap;

import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayRes;

public interface IRestClient<T extends BaseDtoRes> {
//	BaseWebGatewayRes<T> getResponse(BaseWebGatewayRes<HashMap<String, Object>> gatewayRes);
	T getResponse(HashMap<String, Object> gatewayRes);
}
