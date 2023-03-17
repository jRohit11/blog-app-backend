package com.rj.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.blog.exception.ResourceNotFoundException;
import com.rj.blog.model.Category;
import com.rj.blog.model.Post;
import com.rj.blog.model.User;
import com.rj.blog.payloads.PostDto;
import com.rj.blog.repository.CategoryRepo;
import com.rj.blog.repository.PostRepo;
import com.rj.blog.repository.UserRepo;
import com.rj.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post =  modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());		
		post.setImageName(postDto.getImageName());
		Post updatedPost= postRepo.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		postRepo.delete(post);

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPosts = postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostDto getPostById(int postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsBycategory(int categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("category", "category id", categoryId));
		List<Post> posts = postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect((Collectors.toList()));
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
	    List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
	    return postDtos;
	}

}
