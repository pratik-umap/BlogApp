package com.blogapplication.project.payload;

import com.blogapplication.project.entity.Post;
import com.blogapplication.project.entity.UserEntity;


public class CommentDto {
	
	private int id;
	
	private String content;
	
	private PostDto post;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PostDto getPost() {
		return post;
	}

	public void setPost(PostDto post) {
		this.post = post;
	}


	public CommentDto(int id, String content, PostDto post) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
