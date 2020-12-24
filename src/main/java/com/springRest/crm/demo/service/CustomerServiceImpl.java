package com.springRest.crm.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springRest.crm.demo.dao.CustomerDAO;
import com.springRest.crm.demo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject the session factory
	@Autowired
	private CustomerDAO customerDao;

	/* Delegates the calls from the CustomerService to the actual CustomerDAO. */
	
	@Transactional
	public List<Customer> getCustomer() {
		// Delegate calls to DAO
		return customerDao.getCustomer();
	}

	@Transactional
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}

	@Transactional
	public Customer getCustomer(int theId) {
		return customerDao.getCustomer(theId);
	}

	@Transactional
	public void deleteCustomer(int theId) {
		customerDao.deleteCustomer(theId);

	}

	@Transactional
	public List<Customer> searchCustomer(String theSearchName) {
		return customerDao.searchCustomer(theSearchName);

	}

}
