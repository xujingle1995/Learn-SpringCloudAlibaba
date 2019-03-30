package com.xjl.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xjl.sentinel.handler.SentinelExceptionHandler;

@RestController
public class SentinelTestController {

	@SentinelResource(value = "hello", entryType = EntryType.OUT, blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle")
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		System.out.println(name);
		return "hello " + name;
	}

}
