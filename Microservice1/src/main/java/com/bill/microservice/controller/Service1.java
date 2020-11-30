package com.bill.microservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class Service1 {
	
	@PostMapping(value = "/method1")
	public String method1() {
		return "method1";
	} 
}
