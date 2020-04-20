package com.tp.testProject.service;

import com.tp.testProject.userDTO.UserDTO;

public interface IUserService {
	public boolean userSignUp(final UserDTO user);
	public UserDTO userSignIn(final UserDTO user);
	public boolean userModify(final UserDTO user);
	public boolean userDelete(final UserDTO user);
}
