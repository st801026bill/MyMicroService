package com.bill.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.microservice.util.TestUtil;

@RestController
@RequestMapping("/service1")
public class Service1 {
	
	@Autowired
	TestUtil testUtil;
	
	@PostMapping(value = "/method1")
	public String method1() {
		return "local method1";
	}
	
	@PostMapping(value = "/method2")
	public String callTestUtil() {
		return testUtil.callTestUtil();
	}
}
