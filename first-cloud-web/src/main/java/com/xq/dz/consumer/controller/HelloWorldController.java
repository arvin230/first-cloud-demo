package com.xq.dz.consumer.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.xq.dz.consumer.util.LOG;

@RestController
public class HelloWorldController {

	@Autowired
	private EurekaInstanceConfig eurekaInstanceConfig;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		LOG.COMMON_LOG.info("/hello, instanceId:{}, host:{}", eurekaInstanceConfig.getInstanceId(),
				eurekaInstanceConfig.getHostName(false));
		return restTemplate.getForEntity("http://SERVICE-Arvin/hello", String.class).getBody();
	}

	@RequestMapping(value = "/helloEx", method = RequestMethod.GET)
	public String helloEx() {
		ServiceInstance instance = this.loadBalancerClient.choose("SERVICE-Arvin");
		URI helloUri = URI.create(String.format("http://%s:%s/hello", instance.getHost(), instance.getPort()));
		LOG.COMMON_LOG.info("Target service uri = {}. ", helloUri.toString());
		// 此处不可以使用自动注入的RestTemplate对象，因为会把服务的逻辑名称当做字符串处理
		// return new RestTemplate().getForEntity(helloUri, String.class).getBody();
		// 在application里面单独定义的restTemplate可以使用
		return new RestTemplate().getForEntity(helloUri, String.class).getBody();
	}

}
