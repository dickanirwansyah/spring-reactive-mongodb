package com.spring.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection="product")
public class Product {

	@Id
	private String id;
	private String name;
	private Category category;
	private Integer stock;
	private Integer price;
	
}
