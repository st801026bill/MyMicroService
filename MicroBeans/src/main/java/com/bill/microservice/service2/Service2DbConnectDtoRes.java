package com.bill.microservice.service2;

import java.util.List;

import com.bill.microservice.base.BaseDtoRes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({"DB1_STUDENT", "DB2_STUDENT"})
public class Service2DbConnectDtoRes extends BaseDtoRes {
	@JsonProperty("DB1_STUDENT")
	private List<Student> studentDb1List;
	
	@JsonProperty("DB2_STUDENT")
	private List<Student> studentDb2List;
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Student {
		@JsonProperty("STUDENT_ID")
		private Integer studentId;
		
		@JsonProperty("STUDENT_NAME")
		private String studentName;
	}
}
