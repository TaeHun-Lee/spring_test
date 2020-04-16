package com.tp.testProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.testProject.user.User;
import com.tp.testProject.userdao.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userdao;
	
	public boolean userSignIn(final User user) {
		boolean result = true;
		result = userdao.insertUser(user);
		return result;	
	}
}
