package com.bill.microservice.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggingDto {
	private String txn_seq;
	private String return_code;
	private String return_desc;
	private String server_host;
	private String remote_host;
	private long elapse = 0L;
}
