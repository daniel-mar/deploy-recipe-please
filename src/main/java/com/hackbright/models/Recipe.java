package com.hackbright.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
    
@Entity 
@Table(name="recipes")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="The title cannot be empty.")
	@Size(min=3, max=30, message="Title must be between 3 and 30 characters")
    private String title;
    
    @NotEmpty(message="The creator cannot be empty.")
	 @Size(min=3, max=30, message="Creator must be between 3 and 30 characters")
    private String creator;
    
    @NotEmpty(message="Description cannot be empty")
	 @Size(min=3, max=1000,
	 message="Description must be between 3 and 1000 characters")
    private String description;
    
  
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
	
	//============================================================================
	//Many to One
	//============================================================================
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	//============================================================================
	//CONSTRUCTORS
	//============================================================================

	public Recipe() {
	}

	public Recipe(
			@NotEmpty(message = "The title cannot be empty.") @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters") String title,
			@NotEmpty(message = "The creator cannot be empty.") @Size(min = 3, max = 30, message = "Creator must be between 3 and 30 characters") String creator,
			@NotEmpty(message = "The description cannot be empty.") @Size(min = 3, max = 1000, message = "Description must be between 3 and 1000 characters") String description,
			User user) {
		super();
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.user = user;
	}

	public Recipe(Long id,
			@NotEmpty(message = "The title cannot be empty.") @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters") String title,
			@NotEmpty(message = "The creator cannot be empty.") @Size(min = 3, max = 30, message = "Creator must be between 3 and 30 characters") String creator,
			@NotEmpty(message = "The description cannot be empty.") @Size(min = 3, max = 1000, message = "Description must be between 3 and 1000 characters") String description,
			Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	
	//============================================================================
	//GETTERS & SETTERS
	//============================================================================
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

  
}
    