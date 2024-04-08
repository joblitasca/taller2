package com.vichamalab.test.dto;

import lombok.Data;

@Data
public class Product {
	private Long id;
	private String name;
	private String sku;
	private String description;
	private float price;
}
