package com.capgemini.store.reposervices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.store.beans.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	public Category findByCategoryName(String categoryName);
	
}
