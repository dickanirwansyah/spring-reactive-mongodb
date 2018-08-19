package com.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.entity.Category;
import com.spring.app.repository.CategoryRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
	
	
	@Autowired private CategoryRepository categoryRepository;
	
	@PostMapping
	public Mono<Category> createCategory(@RequestBody Category category){
		Category cat = Category.builder()
				.name(category.getName())
				.build();
		return categoryRepository.save(cat);
	}
}
