package com.spring.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "customer")
public class Customer {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String adress;
}
