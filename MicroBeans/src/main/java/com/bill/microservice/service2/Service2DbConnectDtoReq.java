package com.bill.microservice.service2;

import com.bill.microservice.base.BaseDtoReq;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
public class Service2DbConnectDtoReq extends BaseDtoReq {
}
