package com.blogapplication.project.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.entity.UserRole;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.repository.UserRepository;
import com.blogapplication.project.repository.UserRoleRepository;
import com.blogapplication.project.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserEntity createUser(UserEntity user) {
		user.setCreatedAt(new Date());
	    UserRole userRole = userRoleRepository.findById(1).get();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(userRole);
		UserEntity save = userRepository.save(user);
		return save;
	}

	@Override
	public UserEntity getUserById(int id) throws UserNotFoundException {
		UserEntity user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user not found"));
		return user;
	}

	@Override
	public List<UserEntity> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity updateUser(UserEntity user,int id) throws UserNotFoundException {
		UserEntity updateUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));

		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		UserEntity save = userRepository.save(updateUser);
		return save;
	}

	@Override
	public void deleteUser(int id) throws UserNotFoundException {
		UserEntity user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
		 userRepository.delete(user);
		
	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
