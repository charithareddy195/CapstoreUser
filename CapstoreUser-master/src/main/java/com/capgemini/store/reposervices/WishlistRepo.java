package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.store.beans.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer>,CrudRepository<Wishlist, Integer>{

	public Wishlist findByCustomer(String phoneNumber);

}
