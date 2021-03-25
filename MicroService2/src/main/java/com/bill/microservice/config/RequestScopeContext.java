package com.bill.microservice.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayRes;

import lombok.Data;

@Component
@Data
@RequestScope
public class RequestScopeContext {
	BaseWebGatewayRes<? extends BaseDtoReq> baseWebGatewayReq;
}
