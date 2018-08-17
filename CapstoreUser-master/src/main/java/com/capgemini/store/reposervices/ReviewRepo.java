package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.store.beans.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>,CrudRepository<Review, Integer>{
	
}
