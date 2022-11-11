package com.cybage.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	public User(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
	
}
