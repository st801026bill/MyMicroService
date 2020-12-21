package com.bill.microservice.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({""})
public class BaseWebGatewayRes<T> {
	
	@JsonProperty("MWHEADER")
	private BaseWebGatewayMWHeaderRes mwheader;
	
	@JsonProperty("TRANRS")
	private T tranrs;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BaseWebGatewayMWHeaderRes {
		
		@JsonProperty("TXNSEQ")
		private String txnseq;
		
		@JsonProperty("RETURNCODE")
		private String returncode;
		
		@JsonProperty("RETURNDESC")
		private String returndesc;
	}
}


