package com.rj.blog.service;

import com.rj.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, int postId);
	void deleteComment(int commentId);
}
