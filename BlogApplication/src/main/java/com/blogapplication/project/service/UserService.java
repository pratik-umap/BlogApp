package com.blogapplication.project.service;

import java.util.List;

import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.exception.UserNotFoundException;

public interface UserService {
    
	UserEntity createUser(UserEntity user);
	
	UserEntity findByEmail(String email);
	
	UserEntity getUserById(int id) throws UserNotFoundException;
	
	List<UserEntity> getAllUser();
	
	UserEntity updateUser(UserEntity user,int id) throws UserNotFoundException;
	
	void deleteUser(int id) throws UserNotFoundException;
	
}
