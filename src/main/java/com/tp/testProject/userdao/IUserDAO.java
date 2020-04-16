package com.tp.testProject.userdao;

import java.util.ArrayList;

import com.tp.testProject.user.User;

public interface IUserDAO {
	boolean checkUserInstance(final User user);
	boolean insertUser(final User user);
	User updateUser(final User user);
	boolean deleteUser(final User user);
	User selectUser(final String userId);
	ArrayList<User> selectMultipleUser(final String[] userIds);
}
