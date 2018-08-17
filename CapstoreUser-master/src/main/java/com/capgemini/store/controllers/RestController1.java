package com.capgemini.store.controllers;

import java.lang.ref.PhantomReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.store.beans.Customer;
import com.capgemini.store.exceptions.InvalidInputException;
import com.capgemini.store.services.CapstoreServices;

@RestController
public class RestController1 {
	// String phoneNumber;
	public Customer customer;
	@Autowired
	private CapstoreServices capstoreServices;

	// Customer SignUp
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void signUp(@RequestBody Customer customer) {
		capstoreServices.signUp(customer);
	}

	// CustomerSignIn
	@RequestMapping(value = "/customerSignIn")
	public ResponseEntity<String> customerSignIn(String email, String password) throws InvalidInputException {
		customer = capstoreServices.customerSignIn(email, password);
		String name = customer.getCustomerName();
		return new ResponseEntity<String>(name, HttpStatus.OK);
	}

	// getCustomerDetails
	@RequestMapping(value = "/getCustomerDetails")
	public Customer getCustomerDetails(String phoneNumber)
			throws InvalidInputException {
		customer = capstoreServices.getCustomerDetails(phoneNumber);
		return  customer;
		}

}
