package com.tp.testProject.userdao;

import java.util.ArrayList;
import java.util.List;

import com.tp.testProject.user.User;

public interface IUserDAO {
	boolean checkUserInstance(final User user);
	boolean insertUser(final User user);
	boolean updateUser(final User user);
	boolean deleteUser(final User user);
	User selectUser(final String userId, final String userPw);
	ArrayList<User> selectMultipleUser(final List<String> userIds);
}
