package com.maoqu.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {
	
	@Value("${xjl.name}")
	private String name;
	@Value("${xjl.name1}")
	private String name1;
	
	@RequestMapping("/test")
	public String test(){
		return name+"  " + name1;
	}

}
