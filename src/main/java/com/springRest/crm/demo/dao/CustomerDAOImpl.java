package com.springRest.crm.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springRest.crm.demo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomer() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create query ... sort by last Name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName ", Customer.class);

		// excute the query
		List<Customer> customers = theQuery.getResultList();

		// return results
		return customers;
	}

	public void saveCustomer(Customer customer) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// save the customer in the db
		currentSession.saveOrUpdate(customer);

	}

	public Customer getCustomer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retreive/read from database using the primary key
		Customer customer = currentSession.get(Customer.class, theId);

		return customer;
	}

	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now delete the customer
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("theCustomerId", theId);

		// excute the query
		theQuery.executeUpdate();

	}

	public List<Customer> searchCustomer(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = null;

		// only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;

	}

}
