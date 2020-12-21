package com.bill.microservice.common.exception;

import lombok.Getter;

@Getter
public class ModuleException extends RuntimeException {
	private ErrorType errorType;
	public ModuleException(ErrorType errorType) {
		this.errorType = errorType;
	}
}
