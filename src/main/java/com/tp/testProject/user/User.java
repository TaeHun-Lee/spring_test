package com.tp.testProject.user;

import java.sql.Date;

public class User implements IUser {
	
	private String userId;
	private String userPw;
	private String userEmail;
	private String userAdd;
	private Date userSignedDate;
	
	public User() {
		this.userSignedDate = new Date(new java.util.Date().getTime());
	}

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String getUserPw() {
		return this.userPw;
	}

	@Override
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Override
	public String getUserEmail() {
		return this.userEmail;
	}

	@Override
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String getUserAdd() {
		return this.userAdd;
	}

	@Override
	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}

	@Override
	public Date getUserSignedDate() {
		return this.userSignedDate;
	}

	@Override
	public void setUserSignedDate(Date userSignedDate) {
		this.userSignedDate = userSignedDate;
	}

}
