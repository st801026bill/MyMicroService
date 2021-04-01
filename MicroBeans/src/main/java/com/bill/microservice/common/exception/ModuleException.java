package com.bill.microservice.common.exception;

import lombok.Getter;

@Getter
public class ModuleException extends RuntimeException {
	private ErrorType errorType;
	private String extendMsg;
	
	public ModuleException(ErrorType errorType) {
		this.errorType = errorType;
	}
	
	public ModuleException(ErrorType errorType, String extendMsg) {
		this.errorType = errorType;
		this.extendMsg = extendMsg;
	}
}
