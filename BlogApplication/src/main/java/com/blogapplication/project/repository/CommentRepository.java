package com.blogapplication.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.project.entity.Comment;
import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByUser(UserEntity user);
	
	List<Comment> findByPost(Post post);
	
}
