package com.springRest.crm.demo.CustomerExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandling {

	
	// add an exception for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		CustomerErrorResponse response = new CustomerErrorResponse();
		response.setMessage(exc.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	//add another exception handler to cath any exception	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		CustomerErrorResponse response = new CustomerErrorResponse();
		response.setMessage(exc.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
