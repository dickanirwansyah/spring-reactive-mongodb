package com.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.entity.Product;
import com.spring.app.repository.CategoryRepository;
import com.spring.app.repository.ProductRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
	
	@Autowired ProductRepository productRepository;
	
	@Autowired CategoryRepository categoryRepository;
	
	@PostMapping(value = "/{categoryId}/create")
	public Mono<ResponseEntity<Product>> createProduct(@PathVariable("categoryId")String categoryId,
									                   @RequestBody Product product){
		return categoryRepository.findById(categoryId)
				.flatMap(categoryFlatMap -> {
					product.setCategory(categoryFlatMap);
					return productRepository.save(product);
				})
				.map(resultProduct -> new ResponseEntity<>(resultProduct, HttpStatus.CREATED))
				.defaultIfEmpty(new ResponseEntity<Product>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping(value = "/{categoryId}/create/{productId}")
	public Mono<ResponseEntity<Product>> updateProduct(@PathVariable("categoryId") String categoryId,
												       @PathVariable("productId") String productId,
												       @RequestBody Product product){
		return categoryRepository.findById(categoryId)
				.flatMap(categoryFlatMap -> {
					return productRepository.findById(productId)
							.flatMap(productFlatMap -> {
								productFlatMap.setCategory(categoryFlatMap);
								productFlatMap.setName(product.getName());
								productFlatMap.setPrice(product.getPrice());
								productFlatMap.setStock(product.getStock());
								return productRepository.save(productFlatMap);
							}).map(resultProduct -> new ResponseEntity<>(resultProduct, HttpStatus.CREATED))
							.defaultIfEmpty(new ResponseEntity<Product>(HttpStatus.NOT_FOUND));
				});
	}
}
