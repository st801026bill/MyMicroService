package com.bill.microservice.base;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({""})
public class BaseWebGatewayReq<T> {
	@NotNull(message = "MWHEADER")
	@JsonProperty("MWHEADER")
	private BaseWebGatewayMWHeaderReq mwheader;
	
	@NotNull(message = "TRANRQ")
	@JsonProperty("TRANRQ")
	private T tranrq;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BaseWebGatewayMWHeaderReq {
		
		@Valid
		@NotBlank(message = "TXNSEQ")
		@JsonProperty("TXNSEQ")
		@NotNull
		private String txnseq;
	}
}


