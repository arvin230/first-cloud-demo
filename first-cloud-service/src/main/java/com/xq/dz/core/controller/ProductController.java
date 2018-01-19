package com.xq.dz.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xq.dz.core.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> list() {
		return this.buildProducts();
	}

	@Value("${server.port}")
	private int serverPort = 0;

	@RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
	public Product detail(@PathVariable String itemCode) {
		List<Product> products = this.buildProducts();
		for (Product product : products) {
			if (product.getItemCode().equalsIgnoreCase(itemCode))
			return product;
		}
		return null;
	}

	protected List<Product> buildProducts() {
		String ipCode = "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
		List<Product> products = new ArrayList<>();
		products.add(new Product("item-1", "测试商品-1", "TwoStepsFromJava", 100, ipCode));
		products.add(new Product("item-2", "测试商品-2", "TwoStepsFromJava", 200, ipCode));
		products.add(new Product("item-3", "测试商品-3", "TwoStepsFromJava", 300, ipCode));
		products.add(new Product("item-4", "测试商品-4", "TwoStepsFromJava", 400, ipCode));
		products.add(new Product("item-5", "测试商品-5", "TwoStepsFromJava", 500, ipCode));
		products.add(new Product("item-6", "测试商品-6", "TwoStepsFromJava", 600, ipCode));
		return products;
	}
}
