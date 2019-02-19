package com.tianyuan.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {


	@Value("${log.file}")
	private String logConfig;
	
	@Value("${redis.config}")
	private String redisConfig;
	
	@Value("${local.config}")
	private String localConfig;
	
	@Value("${local.config.invariable}")
	private String localConfigInvariable;

	@RequestMapping("/test")
	public String test() {
		return "logConfig：" + logConfig + " / " + "redisConfig：" + redisConfig + " / " + "localConfig：" + localConfig
				+ " / " + "localConfigInvariable" + localConfigInvariable;
	}
}
