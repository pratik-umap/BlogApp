package com.blogapplication.project.service.Impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.exception.CategoryNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.repository.CategoryRepository;
import com.blogapplication.project.repository.PostRepository;
import com.blogapplication.project.repository.UserRepository;
import com.blogapplication.project.service.PostService;
import com.blogapplication.project.utils.FileUtils;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Post createPost(Post post,int userId,int categoryId) throws UserNotFoundException, CategoryNotFoundException, IOException {
		
		String uploadImage = FileUtils.uploadImage(post);
		
		UserEntity user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
	
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("category not found"));
	
		post.setUser(user);
		post.setCategory(category);
		post.setImagePath(uploadImage);
		
		Post save = postRepository.save(post);
		
		return save;
	}

	@Override
	public List<Post> getAllPost() {
	     List<Post> allPost = postRepository.findAll();
		return allPost;
	}

	@Override
	public Post getPostById(int postId) throws PostNotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found"));
		return post;
	}

	@Override
	public List<Post> getPostByUser(int userId) throws UserNotFoundException {
		UserEntity user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
		
		 List<Post> findByUser = postRepository.findByUser(user);
		 
		 return findByUser;
	}

	@Override
	public List<Post> getPostByCategory(int categoryId) throws CategoryNotFoundException {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
	
		List<Post> findByCategory = postRepository.findByCategory(category);
	     return findByCategory;
	}

	@Override
	public Post updatePost(Post post, int postId) throws PostNotFoundException {
		Post updatePost = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found"));

		updatePost.setTitle(post.getTitle());
		updatePost.setDescription(post.getDescription());
//		updatePost.setImagePath(post.getImagePath());
		
		Post save = postRepository.save(updatePost);
		return save;
	}

	@Override
	public void deletePost(int postId) throws PostNotFoundException {
		Post deletePost = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found"));
		postRepository.delete(deletePost);
		
	}

	@Override
	public List<Post> getPostByKeyword(String keyword) {
		List<Post> allPost = postRepository.findByTitleContaining(keyword);
		
		return allPost;
	}

}
