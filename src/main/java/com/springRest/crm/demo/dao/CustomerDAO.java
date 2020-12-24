package com.springRest.crm.demo.dao;

import java.util.List;

import com.springRest.crm.demo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomer();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theSearchName);
	
}
