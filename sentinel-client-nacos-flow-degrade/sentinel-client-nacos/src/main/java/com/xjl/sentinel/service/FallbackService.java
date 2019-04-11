package com.xjl.sentinel.service;

public interface FallbackService {
	public String getName(String name) throws InterruptedException;

}
