package com.qa.data;

public class Users {

	String name;
	String job;
	String createdAt;
	String id;
	
	public Users(){
		
	}
	
	public Users(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}

	//---- Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
