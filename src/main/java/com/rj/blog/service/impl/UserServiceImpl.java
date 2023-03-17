package com.rj.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.blog.model.User;
import com.rj.blog.payloads.UserDto;
import com.rj.blog.repository.UserRepo;
import com.rj.blog.service.UserService;
import com.rj.blog.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);
		UserDto userDto1 = userToDto(updatedUser);
		return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
		return userToDto(user);
		
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users= userRepo.findAll();
//		List<UserDto> userDtos = null;
//		for(User u : users) {
//			UserDto userD = userToDto(u);
//			userDtos.add(userD);
////			List<UserDto> userDtos = (List<UserDto>) userToDto(i);
//		}
		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList()); //lambda expression
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
		userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}
	
	

}
