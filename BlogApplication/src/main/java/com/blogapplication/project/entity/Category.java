package com.blogapplication.project.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int id;
	
	@NotEmpty(message = "title must not be empty")
	@Column(name = "category_title")
	private String categoryTitle;
	
	@NotEmpty(message = "desc must not be empty")
	@Column(name = "category_desc")
	private String categoryDesc;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	@OneToMany(mappedBy = "category")
	private List<Post> post;

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

	public Category(int id, @NotEmpty(message = "title must not be empty!!") String categoryTitle,
			@NotEmpty(message = "description must not be empty!!") String categoryDesc, Date createdAt) {
		super();
		this.id = id;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.createdAt = createdAt;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryTitle=" + categoryTitle + ", categoryDesc=" + categoryDesc
				+ ", createdAt=" + createdAt + "]";
	}	 
	
}
