package com.xq.dz.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xq.dz.consumer.service.ProductService;
import com.xq.dz.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> list() {
		return this.productService.findAll();
	}

	@HystrixCommand(fallbackMethod = "detailFallback")
	@RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
	public Product detail(@PathVariable String itemCode) {
		return this.productService.loadByItemCode(itemCode);
	}
	
	/**
	 * 
	 * @param itemCode
	 * @return
	 */
	public Product detailFallback(String itemCode) {
		Product product = new Product();
		product.setBandName("无数据");
		product.setIpCode("无提供者");
		product.setName("无名称");
		product.setPrice(-0);
		return product;
	}
}
