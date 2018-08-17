package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
