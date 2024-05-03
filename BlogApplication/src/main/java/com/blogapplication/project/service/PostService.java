package com.blogapplication.project.service;

import java.io.IOException;
import java.util.List;

import com.blogapplication.project.entity.Post;
import com.blogapplication.project.exception.CategoryNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;

public interface PostService {

	Post createPost(Post post,int userId,int categoryId) throws UserNotFoundException, CategoryNotFoundException, IOException;
	
	List<Post> getAllPost();
	
	Post getPostById(int postId) throws PostNotFoundException;
	
	List<Post> getPostByUser(int userId) throws UserNotFoundException;
	
	List<Post> getPostByCategory(int categoryId) throws CategoryNotFoundException;
	
	Post updatePost(Post post,int postId) throws PostNotFoundException;
	
	void deletePost(int postId) throws PostNotFoundException;
	
	List<Post> getPostByKeyword(String keyword);
	
}
