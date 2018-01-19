package com.xq.dz.consumer.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xq.dz.consumer.service.impl.ProductServiceFallback;
import com.xq.dz.core.model.Product;

@FeignClient(name = "SERVICE-Arvin", fallback = ProductServiceFallback.class)
public interface ProductService {

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	List<Product> findAll();

	@RequestMapping(value = "/products/{itemCode}", method = RequestMethod.GET)
	Product loadByItemCode(@PathVariable("itemCode") String itemCode);
}
