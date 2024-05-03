package com.blogapplication.project.payload;

import java.util.Date;
import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {

	private int id;
	
	private String categoryTitle;
	
	private String categoryDesc;
	
	private Date createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CategoryDto(int id,String categoryTitle,String categoryDesc, Date createdAt) {
		super();
		this.id = id;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.createdAt = createdAt;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
