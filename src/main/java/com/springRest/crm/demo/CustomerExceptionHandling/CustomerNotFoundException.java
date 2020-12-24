package com.springRest.crm.demo.CustomerExceptionHandling;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

	
}
