package com.bill.microservice.service1;

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
public class Service1Service2CallDtoRes extends BaseDtoRes {
	@JsonProperty("MESSAGE")
	private String message;
}
