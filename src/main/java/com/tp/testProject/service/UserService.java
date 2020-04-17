package com.tp.testProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.testProject.user.User;
import com.tp.testProject.userdao.UserDAO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDAO userdao;
	
	public boolean userSignUp(final User user) {
		boolean result = false;
		result = userdao.insertUser(user);
		return result;
	}
	
	public User userSignIn(final User user) {
		User signingUser = userdao.selectUser(user.getUserId(), user.getUserPw());
		return signingUser;
	}

	@Override
	public boolean userModify(final User user) {
		boolean result = false;
		result = userdao.updateUser(user);
		return result;
	}

	@Override
	public boolean userDelete(final User user) {
		boolean result = false;
		result = userdao.deleteUser(user);
		return result;
	}
}
