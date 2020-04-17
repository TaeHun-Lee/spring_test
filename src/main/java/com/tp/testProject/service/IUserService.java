package com.tp.testProject.service;

import com.tp.testProject.user.User;

public interface IUserService {
	public boolean userSignUp(final User user);
	public User userSignIn(final User user);
	public boolean userModify(final User user);
	public boolean userDelete(final User user);
}
