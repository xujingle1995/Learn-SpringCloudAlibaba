package com.xjl.sentinel.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xjl.sentinel.service.FallbackService;

@Service
public class FallbackServiceImpl implements FallbackService {

	@Override
	@SentinelResource(value = "getName", fallback = "getNameFallback")
	public String getName(String name) {
		for (int i = 0; i < 100000000L; i++) {
		}
		return "getName " + name;
	}
	
	public String getNameFallback(String name){
		return "getNameFallback";
	}


}
