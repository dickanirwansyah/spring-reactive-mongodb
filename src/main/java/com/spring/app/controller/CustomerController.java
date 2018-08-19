package com.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.entity.Customer;
import com.spring.app.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {
	
	@Autowired private CustomerRepository customerRepository;
	
	@GetMapping
	public Flux<Customer> listCustomer(){
		return customerRepository.findAll();
	}
	
	@PostMapping
	public Mono<Customer> createCustomer(@RequestBody Customer customer){
		Customer cs = Customer.builder()
				.name(customer.getName())
				.email(customer.getEmail())
				.adress(customer.getAdress())
				.build();
		
		return customerRepository.save(cs);
	}
	
	@GetMapping(value="/{customerId}")
	public Mono<ResponseEntity<Customer>> getCustomerId(@PathVariable("customerId")String customerId){
		return customerRepository.findById(customerId)
				.map(resultCustomer -> ResponseEntity.ok(resultCustomer))
				.defaultIfEmpty(ResponseEntity
						.notFound()
						.build());
	}
	
	@DeleteMapping(value="/{customerId}")
	public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable("customerId")String customerId){
		return customerRepository.findById(customerId)
				.flatMap(customerFlatMap -> 
						customerRepository.delete(customerFlatMap)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
								.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping(value="/{customerId}")
	public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable("customerId")String customerId,
														 @RequestBody Customer customer){
		return customerRepository.findById(customerId)
				.flatMap(customerFlatMap -> {
					customerFlatMap.setName(customer.getName());
					customerFlatMap.setAdress(customer.getAdress());
					customerFlatMap.setEmail(customer.getEmail());
					return customerRepository.save(customerFlatMap);
				})
				.map(resultEdit -> new ResponseEntity<>(resultEdit, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
