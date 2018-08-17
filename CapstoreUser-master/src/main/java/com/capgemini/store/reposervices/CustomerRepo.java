package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.store.beans.Customer;
import com.capgemini.store.beans.Product;

public interface CustomerRepo extends JpaRepository<Customer, String>,CrudRepository<Customer, String>{
	public Customer findByEmail(String email);
}

