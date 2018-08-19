package com.spring.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.app.entity.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String>{

}
