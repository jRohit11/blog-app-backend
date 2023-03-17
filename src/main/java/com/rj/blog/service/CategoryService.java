package com.rj.blog.service;

import java.util.List;

import com.rj.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	
	//delete
	void deleteCategory(int categoryId);
	
	//get category by id
	CategoryDto getCategory(int categoryId);
	
	//get all category
	List<CategoryDto> getCategories();
	
}
