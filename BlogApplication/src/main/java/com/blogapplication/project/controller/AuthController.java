package com.blogapplication.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.payload.JwtRequest;
import com.blogapplication.project.payload.JwtResponse;
import com.blogapplication.project.security.JwtHelper;
import com.blogapplication.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired	
	private JwtHelper jwtHelper;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create-user")
	public ResponseEntity<?> createUser(@RequestBody UserEntity user){
		
		UserEntity findByEmail = userService.findByEmail(user.getEmail());
		if (findByEmail == null) {
			userService.createUser(user);
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
			String token = jwtHelper.generateToken(userDetails);
			return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("user already Exist");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestBody JwtRequest request){
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());	
		authenticationManager.authenticate(authentication);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	    String token = jwtHelper.generateToken(userDetails);
	   return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request,HttpServletResponse response){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
	    
		return ResponseEntity.status(HttpStatus.OK).body("user logout successfully !!");
	}

	
}
