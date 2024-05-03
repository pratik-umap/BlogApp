package com.blogapplication.project.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.project.entity.Comment;
import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;
import com.blogapplication.project.exception.CommentNotFoundException;
import com.blogapplication.project.exception.PostNotFoundException;
import com.blogapplication.project.exception.UserNotFoundException;
import com.blogapplication.project.repository.CommentRepository;
import com.blogapplication.project.repository.PostRepository;
import com.blogapplication.project.repository.UserRepository;
import com.blogapplication.project.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment createComment(Comment comment, int userId, int postId) throws UserNotFoundException, PostNotFoundException {
		UserEntity user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
	
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found"));
	
		
		comment.setUser(user);
		comment.setPost(post);
		
		Comment save = commentRepository.save(comment);
		return save;
	}

	@Override
	public List<Comment> getAllComment() {
		List<Comment> allComment = commentRepository.findAll();
		 return allComment;
	}

	@Override
	public Comment getCommentById(int commentId) throws CommentNotFoundException {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("comment not found"));
		return comment;
	}

	@Override
	public List<Comment> getCommentByUser(int userId) throws UserNotFoundException {
		  UserEntity user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
		 List<Comment> findByUser = commentRepository.findByUser(user);
		 return findByUser;
	}

	@Override
	public List<Comment> getCommentByPost(int postId) throws PostNotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found"));
	
		List<Comment> findByPost = commentRepository.findByPost(post);
		return findByPost;
	}

	@Override
	public Comment updateComment(Comment comment, int commentId) throws CommentNotFoundException {
		Comment updateComment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("comment not found"));
	
		updateComment.setContent(comment.getContent());
		
		Comment save = commentRepository.save(updateComment);
		return save;
	}

	@Override
	public void deleteComment(int commentId) throws CommentNotFoundException {
		Comment deleteComment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("comment not found"));
		commentRepository.delete(deleteComment);
		
	}

}
