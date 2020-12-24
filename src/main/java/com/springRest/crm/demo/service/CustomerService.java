package com.springRest.crm.demo.service;

import java.util.List;

import com.springRest.crm.demo.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomers(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theSearchName);

}
