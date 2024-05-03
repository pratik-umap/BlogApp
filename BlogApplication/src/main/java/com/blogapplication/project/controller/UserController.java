package com.blogapplication.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.payload.UserDto;
import com.blogapplication.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserEntity user) {
		UserEntity createUser = userService.createUser(user);
		UserDto userMap = modelMapper.map(createUser, UserDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userMap);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int id) throws UserNotFoundException{

			UserEntity user = userService.getUserById(id);
			UserDto userMap = modelMapper.map(user, UserDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(userMap);
	}
	
	@GetMapping()
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserEntity> allUser = userService.getAllUser();
		List<UserDto> allUserMap = allUser.stream().map((user)-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(allUserMap);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserEntity user,@PathVariable int id) throws UserNotFoundException{
			UserEntity updateUser = userService.updateUser(user, id);
			UserDto userMap = modelMapper.map(updateUser, UserDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(userMap);

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException{
		
			userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully");
		
	}
	
	
}
