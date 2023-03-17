package com.rj.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rj.blog.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByName(String name);
}
