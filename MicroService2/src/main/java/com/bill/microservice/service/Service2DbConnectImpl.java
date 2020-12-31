package com.bill.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.microservice.base.BaseDtoReq;
import com.bill.microservice.base.BaseWebGatewayReq;
import com.bill.microservice.base.IBaseService;
import com.bill.microservice.dao.db1.StudentDb1Dao;
import com.bill.microservice.dao.db2.StudentDb2Dao;
import com.bill.microservice.service2.Service2DbConnectDtoReq;
import com.bill.microservice.service2.Service2DbConnectDtoRes;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Service2DbConnectImpl implements IBaseService<Service2DbConnectDtoRes>{
	
	@Autowired
	StudentDb1Dao studentDb1Dao;
	@Autowired
	StudentDb2Dao studentDb2Dao;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public Service2DbConnectDtoRes process(BaseWebGatewayReq<? extends BaseDtoReq> gatewayReq) {
		// TODO Auto-generated method stub
		Service2DbConnectDtoReq request = (Service2DbConnectDtoReq) gatewayReq.getTranrq();
		log.info("Service2FeignCallDtoReq: {}", request);
		
		List<Service2DbConnectDtoRes.Student> resStudentDb1List = getStudentDb1();
		List<Service2DbConnectDtoRes.Student> resStudentDb2List = getStudentDb2();

		Service2DbConnectDtoRes res = Service2DbConnectDtoRes.builder()
				.studentDb1List(resStudentDb1List)
				.studentDb2List(resStudentDb2List)
				.build();
		return res;
	}
	
	private List<Service2DbConnectDtoRes.Student> getStudentDb1() {
		List<Service2DbConnectDtoRes.Student> resStudentDb1List = new ArrayList<>();
		List<com.bill.microservice.dao.db1.model.Student> studentDb1List = studentDb1Dao.findAll();
		studentDb1List.stream().forEach(student1 -> {
			resStudentDb1List.add(new Service2DbConnectDtoRes.Student(student1.getStudentId(), student1.getStudentName()));
		});
		return resStudentDb1List;
	}
	
	private List<Service2DbConnectDtoRes.Student> getStudentDb2() {
		List<Service2DbConnectDtoRes.Student> resStudentDb2List = new ArrayList<>();
		List<com.bill.microservice.dao.db2.model.Student> studentDb2List = studentDb2Dao.findAll();
		studentDb2List.stream().forEach(student2 -> {
			resStudentDb2List.add(new Service2DbConnectDtoRes.Student(student2.getStudentId(), student2.getStudentName()));
		});
		return resStudentDb2List;
	}
	
}
