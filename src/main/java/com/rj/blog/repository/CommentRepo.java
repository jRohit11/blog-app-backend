package com.rj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rj.blog.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
