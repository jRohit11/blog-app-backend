package com.rj.blog.service;

import java.util.List;

import com.rj.blog.payloads.PostDto;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	//post
	PostDto updatePost(PostDto postDto, int postId);
	
	//delete
	void deletePost(int postId);
	
	//get all posts
	List<PostDto> getAllPost();
	
	//get single post
	PostDto getPostById(int postId);
	
	//get all post by category
	List<PostDto> getPostsBycategory(int categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(int userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
}
