package com.tp.testProject.userdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tp.testProject.userDTO.UserDTO;

@Repository
public class UserDAO implements IUserDAO {
	
	private JdbcTemplate template;
	
	@Autowired
	public UserDAO(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean checkUserInstance(final UserDTO user) {
		boolean result = false;
		String sql = "select EXISTS (select * from USER where userId=?) as success";
		System.out.println("유저 인스턴스 체크");
		List<Integer> res = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());;
			}
		}, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer result = rs.getInt(1);
				return result;
			}
		});
		System.out.println("중복체크" + res.get(0));
		if (res.get(0) == 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean insertUser(final UserDTO user) {
		boolean result = false;
		boolean duplicityCheck = checkUserInstance(user);
		if(!duplicityCheck) {
			return false;
		}
		String sql = "INSERT INTO USER(userId, userPw, userEmail, userAdd, userSignedDate) VALUES(?, ?, ?, ?, ?)";
		System.out.println("DB 인서트");
		int rs = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getUserPw());
				ps.setString(3, user.getUserEmail());
				ps.setString(4, user.getUserAdd());
				ps.setDate(5, user.getUserSignedDate());
			}
			
		});
		if (rs == 1) result = true;
		return result;
	}

	@Override
	public boolean updateUser(final UserDTO user) {
		boolean result = false;
		String sql = "UPDATE USER SET userPw=?, userEmail=?, userAdd=?, userSignedDate=? WHERE userId=?";
		System.out.println("DB 업데이트");
		int rs = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserPw());
				ps.setString(2, user.getUserEmail());
				ps.setString(3, user.getUserAdd());
				ps.setDate(4, user.getUserSignedDate());
				ps.setString(5, user.getUserId());
			}
			
		});
		if (rs == 1) result = true;
		return result;
	}

	@Override
	public boolean deleteUser(final UserDTO user) {
		boolean result = false;
		String sql = "DELETE FROM USER WHERE userId=? AND userPW=?";
		System.out.println("DB 딜리트");
		int rs = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getUserPw());
			}
			
		});
		if (rs == 1) result = true;
		return result;
	}

	@Override
	public UserDTO selectUser(final String userId, final String userPw) {
		String sql = "SELECT * FROM USER WHERE userId=? AND userPw=?";
		System.out.println("DB 셀렉트");
		List<UserDTO> selected = template.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userId);
				ps.setString(2, userPw);
			}
			
		}, new RowMapper<UserDTO>() {

			@Override
			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserDTO user = new UserDTO();
				user.setUserId(rs.getString("userId"));
				user.setUserPw(rs.getString("userPw"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserAdd(rs.getString("userAdd"));
				user.setUserSignedDate(rs.getDate("userSignedDate"));
				return user;
			}
		});
		
		if (selected.isEmpty()) return null;
		return selected.get(0);
	}

	@Override
	public ArrayList<UserDTO> selectMultipleUser(final List<String> userIds) {
		String sql = "SELECT * FROM USER WHERE userId=?";
		System.out.println("DB ��Ƽ�� ����Ʈ");
		ArrayList<UserDTO> selected = new ArrayList<UserDTO>();
		while(!userIds.isEmpty()) {
			UserDTO user = template.queryForObject(sql, new RowMapper<UserDTO>() {

				@Override
				public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserDTO user = new UserDTO();
					user.setUserId(rs.getString("userId"));
					user.setUserPw(rs.getString("userPw"));
					user.setUserEmail(rs.getString("userEmail"));
					user.setUserAdd(rs.getString("userAdd"));
					user.setUserSignedDate(rs.getDate("userSignedDate"));
					return user;
				}
			}, userIds.get(0));
			userIds.remove(0);
			selected.add(user);
		}
		
		if (selected.isEmpty()) return null;
		return selected;
	}
	
}
