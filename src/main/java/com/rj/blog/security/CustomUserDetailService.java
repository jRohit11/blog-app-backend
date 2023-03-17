package com.rj.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rj.blog.exception.ResourceNotFoundException;
import com.rj.blog.model.User;
import com.rj.blog.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//loading user from database by username
		User user = userRepo.findByName(userName).orElseThrow(()-> new ResourceNotFoundException("User", "user_Name:" +userName, 0));
		
		return user;
	}
	
	
}
