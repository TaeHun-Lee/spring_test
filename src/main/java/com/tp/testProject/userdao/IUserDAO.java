package com.tp.testProject.userdao;

import java.util.ArrayList;
import java.util.List;

import com.tp.testProject.userDTO.UserDTO;

public interface IUserDAO {
	boolean checkUserInstance(final UserDTO user);
	boolean insertUser(final UserDTO user);
	boolean updateUser(final UserDTO user);
	boolean deleteUser(final UserDTO user);
	UserDTO selectUser(final String userId, final String userPw);
	ArrayList<UserDTO> selectMultipleUser(final List<String> userIds);
}
