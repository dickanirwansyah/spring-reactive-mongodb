package com.spring.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.app.entity.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

}
