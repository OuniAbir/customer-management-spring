package com.springRest.crm.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springRest.crm.demo.CustomerExceptionHandling.CustomerNotFoundException;
import com.springRest.crm.demo.entity.Customer;
import com.springRest.crm.demo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustumer() {

		return customerService.getCustomer();
	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Custmer id not found -  " + customerId);
		}
		return theCustomer;
	}

}
