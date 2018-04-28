package com.xq.dz.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 7405544346046733501L;

	private String itemCode; // 产品货号
	private String name; // 产品名称
	private String bandName; // 产品品牌名称
	private int price; // 产品价格(分)
	private String ipCode; // 提供服务的机器ip

	public Product() {
	}

	public Product(String itemCode, String name, String bandName, int price, String ipCode) {
		this.itemCode = itemCode;
		this.name = name;
		this.bandName = bandName;
		this.price = price;
		this.ipCode = ipCode;
	}
}
