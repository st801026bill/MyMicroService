package com.bill.microservice.common.enums;

public enum ServiceMappingEnum {

//	SERVICE1 ("microservice1", "8080", "/service1/service2/call"),	
//	SERVICE2_FEIGN_CALL ("microservice2", "8081", "/service2/feign/call");
	
	SERVICE1 			("http://localhost", "8080", "/service1/service2/call"),
	SERVICE2_FEIGN_CALL ("http://localhost", "8081", "/service2/feign/call");
	
	String name;
	String port;
	String uri;
	
	private ServiceMappingEnum(String name, String port, String uri) {
		this.name = name;
		this.port = port;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public String getPort() {
		return port;
	}

	public String getUri() {
		return uri;
	}
}
