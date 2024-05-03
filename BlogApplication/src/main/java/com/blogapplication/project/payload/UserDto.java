package com.blogapplication.project.payload;

import java.util.Date;


public class UserDto {

	private int id;
	
	private String name;
	
	private String email;
	
	private Date createdAt;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public UserDto(int id, String name, String email, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
