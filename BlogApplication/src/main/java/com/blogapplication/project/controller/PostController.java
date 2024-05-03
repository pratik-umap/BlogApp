package com.blogapplication.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.project.entity.Post;
import com.blogapplication.project.exception.CategoryNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.payload.ApiResponse;
import com.blogapplication.project.payload.PostDto;
import com.blogapplication.project.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	public ResponseEntity<PostDto> createPost(@ModelAttribute Post post,@RequestParam int userId,@RequestParam int categoryId) throws UserNotFoundException, CategoryNotFoundException, IOException {
		
			Post createPost = postService.createPost(post, userId, categoryId);
			PostDto postDto = modelMapper.map(createPost, PostDto.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
	}
	
	@GetMapping()
	public ResponseEntity<List<PostDto>> getAllPost(){
		try {
			List<Post> allPost = postService.getAllPost();
			List<PostDto> allPostMap = allPost.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(allPostMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id) throws PostNotFoundException{
	
			Post post = postService.getPostById(id);
			PostDto postdto = modelMapper.map(post, PostDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(postdto);
	
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int id) throws UserNotFoundException{
	
			List<Post> postByUser = postService.getPostByUser(id);
			List<PostDto> collect = postByUser.stream().map((post)-> modelMapper.map(postByUser, PostDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(collect);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int id) throws CategoryNotFoundException{
		
			List<Post> postByCategory = postService.getPostByCategory(id);
			List<PostDto> postByCategoryMap = postByCategory.stream().map((post)-> modelMapper.map(postByCategory, PostDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(postByCategoryMap);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<PostDto>> getPostByCategory(@RequestParam String keyword){
		try {
			 List<Post> postByKeyword = postService.getPostByKeyword(keyword);
			 List<PostDto> postByKeywordMap = postByKeyword.stream().map((post)-> modelMapper.map(postByKeyword, PostDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(postByKeywordMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody Post post,@PathVariable int id) throws PostNotFoundException{
		
			Post updatePost = postService.updatePost(post, id);
			PostDto updatePostMap = modelMapper.map(updatePost, PostDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(updatePostMap);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int id) throws PostNotFoundException{

			postService.deletePost(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("post deleted successfully", "true"));
		
	}
}
