package com.rj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rj.blog.model.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
	
}
