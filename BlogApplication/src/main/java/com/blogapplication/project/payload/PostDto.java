package com.blogapplication.project.payload;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.entity.Comment;
import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {

	private int id;
	
	@NotEmpty(message = "plz enter post title")
	private String title;
	
	@NotEmpty(message = "plz enter post desc")
	private String description;
	
	private String imagePath;
	
	private CategoryDto category;
	
	private UserDto user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public PostDto(int id, @NotEmpty(message = "plz enter post title") String title,
			@NotEmpty(message = "plz enter post desc") String description, String imagePath, CategoryDto category,
			UserDto user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.imagePath = imagePath;
		this.category = category;
		this.user = user;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
