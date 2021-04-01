package com.bill.microservice.rest.client;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseDtoRes;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.base.BaseWebGatewayRes.BaseWebGatewayMWHeaderRes;
import com.bill.microservice.common.exception.ErrorType;
import com.bill.microservice.common.exception.ModuleException;

public abstract class BaseRestUtil<Request extends BaseDtoReq, Response extends BaseDtoRes> {
	
	private static final boolean ENABLED_ASSERT = false;
	
//	public final BaseWebGatewayRes<Response> sendRestClientRequest(Request baseDtoReq) {
//		//打包baseDtoReq
//		BaseWebGatewayReq<Request> baseWebReq = buildRestRequest(baseDtoReq);
//		//調用RestClient
//		BaseWebGatewayRes<Response> baseWebRes = invokeRestClient(baseWebReq);
//		//是否啟用assertRestResult(預設不啟用)
//		if(enabledAssert()) assertRestResult(baseWebRes);
//		
//		return baseWebRes;
//	}
	
	public final Response sendRestClientRequest(Request baseDtoReq) {
		//打包baseDtoReq
		BaseWebGatewayReq<Request> baseWebReq = buildRestRequest(baseDtoReq);
		//調用RestClient
//		BaseWebGatewayRes<Response> baseWebRes = invokeRestClient(baseWebReq);
		Response baseWebRes = invokeRestClient(baseWebReq);
		//是否啟用assertRestResult(預設不啟用)
//		if(enabledAssert()) assertRestResult(baseWebRes);
		
		return baseWebRes;
	}
	
	protected abstract BaseWebGatewayReq<Request> buildRestRequest(Request baseDtoReq) throws ModuleException;
	
//	protected abstract BaseWebGatewayRes<Response> invokeRestClient(BaseWebGatewayReq<Request> gatewayReq);
	protected abstract Response invokeRestClient(BaseWebGatewayReq<Request> gatewayReq);
	
	
	private void assertRestResult(BaseWebGatewayRes<Response> res) {
		BaseWebGatewayMWHeaderRes resMwHeader = res.getMwheader();
		if(!res.getMwheader().equals(ErrorType.SUCCESS))
			throw new ModuleException(ErrorType.find(resMwHeader.getReturncode()), resMwHeader.getReturndesc());
	}
	
	protected boolean enabledAssert() {
		return ENABLED_ASSERT;
	}
}
