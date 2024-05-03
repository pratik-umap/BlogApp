package com.blogapplication.project.service;

import java.util.List;

import com.blogapplication.project.entity.Comment;
import com.blogapplication.project.exception.CommentNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;

public interface CommentService {

	 Comment createComment(Comment comment,int userId,int postId) throws UserNotFoundException, PostNotFoundException;
	 
	 List<Comment> getAllComment();
	 
	 Comment getCommentById(int commentId) throws CommentNotFoundException;
	 
	 List<Comment> getCommentByUser(int userId) throws UserNotFoundException;
	 
	 List<Comment> getCommentByPost(int postId) throws PostNotFoundException;
	 
	 Comment updateComment(Comment comment,int commentId) throws CommentNotFoundException;
	 
	 void deleteComment(int commentId) throws CommentNotFoundException;

} 
