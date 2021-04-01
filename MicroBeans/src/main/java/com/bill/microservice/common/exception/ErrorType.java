package com.bill.microservice.common.exception;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ErrorType {
	
	SUCCESS("0000", "執行成功"),
	
	DATA_NOT_FOUND_ERROR("1000", "查無資料"),
	
	UNKNOW_ERROR("9999", "未知錯誤");
	
	private String code;
	private String message;
	
	private static final Map<String, ErrorType> lookup = new HashMap<String, ErrorType>();
	static {
		for(ErrorType e : EnumSet.allOf(ErrorType.class)) {
			lookup.put(e.code, e);
		}
	}
	
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
	
	public static ErrorType find(String code) {
		ErrorType type = lookup.get(code);
		if(type == null) 
			return ErrorType.UNKNOW_ERROR;
		return type;
	}
}
