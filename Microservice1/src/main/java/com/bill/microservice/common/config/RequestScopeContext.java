package com.bill.microservice.common.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;

import lombok.Data;

@Component
@Data
@RequestScope
public class RequestScopeContext<T extends BaseDtoReq> {
	BaseWebGatewayReq<T> baseWebGatewayReq;
}
