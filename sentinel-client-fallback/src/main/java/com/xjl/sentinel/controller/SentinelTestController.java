package com.xjl.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.xjl.sentinel.service.FallbackService;

@RestController
public class SentinelTestController {
	
	@Autowired
	private FallbackService service;

	@GetMapping("/getName/{name}")
	public String getName(@PathVariable("name") String name) throws InterruptedException, DegradeException {
		System.out.println(name);
		return service.getName(name);
	}

}
