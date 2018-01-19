package com.xq.dz.consumer.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.xq.dz.consumer.service.ProductService;
import com.xq.dz.core.model.Product;

@Component
public class ProductServiceFallback implements ProductService {

	@Override
	public List<Product> findAll() {
		return Collections.emptyList();
	}

	@Override
	public Product loadByItemCode(@PathVariable("itemCode") String itemCode) {
		return new Product("error", "未知", "TwoStepsFromJava-Fallback", 0, "");
	}

}
