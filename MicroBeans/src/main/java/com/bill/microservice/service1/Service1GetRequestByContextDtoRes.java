package com.bill.microservice.service1;

import com.bill.microservice.base.BaseDtoRes;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Service1GetRequestByContextDtoRes extends BaseDtoRes {
	@JsonProperty("MESSAGE")
	private String message;
}
