package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.store.beans.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>,CrudRepository<Product, Integer>{
	public Product findByProductId(int productId);
}
