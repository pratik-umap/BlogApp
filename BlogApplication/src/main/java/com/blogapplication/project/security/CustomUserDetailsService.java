package com.blogapplication.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		   UserEntity user = userRepository.findByEmail(username);
		   
		   
		return User.builder()
			   .username(user.getEmail())
			   .password(user.getPassword())
			   .roles(user.getUserRole().getUserRole())
			   .build();
	}

}
