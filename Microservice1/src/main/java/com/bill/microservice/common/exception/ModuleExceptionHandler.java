package com.bill.microservice.common.exception;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bill.microservice.base.BaseWebGatewayRes;
import com.bill.microservice.base.BaseWebGatewayRes.BaseWebGatewayMWHeaderRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ModuleExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public BaseWebGatewayRes<HashMap<String, Object>> handle(Exception ex) {
		ex.printStackTrace();
		BaseWebGatewayRes<HashMap<String, Object>> res = new BaseWebGatewayRes<>(
				new BaseWebGatewayMWHeaderRes("", ErrorType.UNKNOW_ERROR.getCode(), ErrorType.UNKNOW_ERROR.getMessage()), 
				new HashMap<>());
		return res;
	}
	
	@ExceptionHandler(ModuleException.class)
	public BaseWebGatewayRes<HashMap<String, Object>> handleModule(ModuleException ex) {
		ex.printStackTrace();
		BaseWebGatewayRes<HashMap<String, Object>> res = new BaseWebGatewayRes<>(
						new BaseWebGatewayMWHeaderRes("", ex.getErrorType().getCode(), ex.getErrorType().getMessage()), 
						new HashMap<>());
		return res;
	}
}
