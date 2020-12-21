package com.bill.microservice.common.exception;

public enum ErrorType {
	
	SUCCESS("0000", "執行成功"),
	
	DATA_NOT_FOUND_ERROR("1000", "查無資料"),
	
	UNKNOW_ERROR("9999", "未知錯誤");
	
	private String code;
	private String message;
	
	ErrorType(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
}
