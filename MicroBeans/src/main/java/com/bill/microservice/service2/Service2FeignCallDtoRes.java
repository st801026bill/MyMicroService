package com.bill.microservice.service2;

import com.bill.microservice.base.BaseDtoRes;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Service2FeignCallDtoRes extends BaseDtoRes {
	@JsonProperty("MESSAGE")
	private String message;
}
