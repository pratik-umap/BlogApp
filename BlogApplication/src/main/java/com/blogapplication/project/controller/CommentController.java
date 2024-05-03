package com.blogapplication.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.project.entity.Comment;
import com.blogapplication.project.exception.CommentNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.payload.ApiResponse;
import com.blogapplication.project.payload.CommentDto;
import com.blogapplication.project.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired 
	ModelMapper modelMapper;
	
	@PostMapping()
	public ResponseEntity<CommentDto> createComment(@RequestBody Comment comment,@RequestParam int userId,@RequestParam int postId) throws UserNotFoundException, PostNotFoundException{
		
			Comment createComment = commentService.createComment(comment, userId, postId);
			CommentDto commentMap = modelMapper.map(createComment, CommentDto.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(commentMap);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<CommentDto>> findAllComment(){
		try {
			List<Comment> allComment = commentService.getAllComment();
			List<CommentDto> allCommentMap = allComment.stream().map((comment)-> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
			return  ResponseEntity.status(HttpStatus.OK).body(allCommentMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> findById(@PathVariable int id) throws CommentNotFoundException{
	
			Comment commentById = commentService.getCommentById(id);
			CommentDto commentMap = modelMapper.map(commentById, CommentDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(commentMap);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<CommentDto>> findByUser(@PathVariable int userId) throws UserNotFoundException{
	
			List<Comment> commentByUser = commentService.getCommentByUser(userId);
			List<CommentDto> commentMap = commentByUser.stream().map((comment)-> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(commentMap);

	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentDto>> findByPost(@PathVariable int postId) throws PostNotFoundException{
		
			List<Comment> commentByPost = commentService.getCommentByPost(postId);
			List<CommentDto> commentMap = commentByPost.stream().map((comment)-> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(commentMap);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody Comment comment,@PathVariable int id) throws CommentNotFoundException{
	
			Comment updateComment = commentService.updateComment(comment, id);
			CommentDto commentMap = modelMapper.map(updateComment, CommentDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(commentMap);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int id) throws CommentNotFoundException{
	
			commentService.deleteComment(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("comment deleted successfully", "true"));
		
	}
	
}
