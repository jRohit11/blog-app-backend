package com.rj.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.blog.exception.ResourceNotFoundException;
import com.rj.blog.model.Category;
import com.rj.blog.payloads.CategoryDto;
import com.rj.blog.repository.CategoryRepo;
import com.rj.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = modelMapper.map(categoryDto, Category.class);
		Category addedCat = categoryRepo.save(cat);
		
		return modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat = categoryRepo.save(cat);
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto>catDtos = categories.stream().map((cat) -> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}
	

}
