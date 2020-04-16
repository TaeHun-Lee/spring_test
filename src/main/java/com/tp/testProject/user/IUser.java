package com.tp.testProject.user;

import java.sql.Date;

public interface IUser {
	String getUserId();
	void setUserId(String userId);
	String getUserPw();
	void setUserPw(String userPw);
	String getUserEmail();
	void setUserEmail(String userEmail);
	String getUserAdd();
	void setUserAdd(String userAdd);
	Date getUserSignedDate();
	void setUserSignedDate(Date userSignedDate);
}
