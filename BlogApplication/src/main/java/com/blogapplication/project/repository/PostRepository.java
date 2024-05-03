package com.blogapplication.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUser(UserEntity user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String keyword);
	
}
