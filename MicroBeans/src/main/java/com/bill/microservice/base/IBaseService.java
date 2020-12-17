package com.bill.microservice.base;

public interface IBaseService<T> {
	T process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq);
}
