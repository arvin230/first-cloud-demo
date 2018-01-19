package com.xq.dz.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.xq.dz.core.util.LOG;

@RestController
public class HelloWorldController {

	@Autowired
	private EurekaInstanceConfig eurekaInstanceConfig;
	@Value("${server.port}")
	private int serverPort = 0;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		LOG.COMMON_LOG.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(),
				eurekaInstanceConfig.getHostName(false));
		return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
	}
	
}
