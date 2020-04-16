package com.tp.testProject.userdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tp.testProject.user.User;

@Repository
public class UserDAO implements IUserDAO {
	
	private JdbcTemplate template;
	
	@Autowired
	public UserDAO(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean checkUserInstance(final User user) {
		boolean result = true;
		String sql = "select EXISTS (select * from USER where userId=?) as success";
		System.out.println("유저 인스턴스 체크");
		switch (template.queryForInt(sql, user.getUserId())) {
		case 1:
			System.out.println("유저 인스턴스 존재");
			result = false;
			break;
		}
		return result;
	}

	@Override
	public boolean insertUser(final User user) {
		boolean result = true;
		boolean duplicityCheck = checkUserInstance(user);
		if(!duplicityCheck) {
			return false;
		}
		String sql = "INSERT INTO USER(userId, userPw, userEmail, userAdd, userSignedDate) VALUES(?, ?, ?, ?, ?)";
		System.out.println("DB 인서트");
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getUserPw());
				ps.setString(3, user.getUserEmail());
				ps.setString(4, user.getUserAdd());
				ps.setDate(5, user.getUserSignedDate());
			}
			
		});
		return result;
	}

	@Override
	public User updateUser(final User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(final User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User selectUser(final String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> selectMultipleUser(final String[] userIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
