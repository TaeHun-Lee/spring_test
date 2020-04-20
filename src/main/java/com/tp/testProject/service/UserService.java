package com.tp.testProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.testProject.userDTO.UserDTO;
import com.tp.testProject.userdao.UserDAO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDAO userdao;
	
	public boolean userSignUp(final UserDTO user) {
		boolean result = false;
		result = userdao.insertUser(user);
		return result;
	}
	
	public UserDTO userSignIn(final UserDTO user) {
		UserDTO signingUser = userdao.selectUser(user.getUserId(), user.getUserPw());
		return signingUser;
	}

	@Override
	public boolean userModify(final UserDTO user) {
		boolean result = false;
		result = userdao.updateUser(user);
		return result;
	}

	@Override
	public boolean userDelete(final UserDTO user) {
		boolean result = false;
		result = userdao.deleteUser(user);
		return result;
	}
}
