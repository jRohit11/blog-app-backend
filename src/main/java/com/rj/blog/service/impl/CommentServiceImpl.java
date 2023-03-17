package com.rj.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.blog.exception.ResourceNotFoundException;
import com.rj.blog.model.Comment;
import com.rj.blog.model.Post;
import com.rj.blog.payloads.CommentDto;
import com.rj.blog.repository.CommentRepo;
import com.rj.blog.repository.PostRepo;
import com.rj.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment com = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		commentRepo.delete(com);

	}

}
