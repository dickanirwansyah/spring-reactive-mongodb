package com.spring.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.app.entity.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{

}
