package com.mahar.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mahar.contact.model.User;

public class UserDAOImpl implements UserDAO{

	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl (DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		if (user.getId() > 0) {
			// update
			String sql = "UPDATE users SET login=?, password=? WHERE id=?";
			jdbcTemplate.update(sql, new Object[] {user.getLogin(), user.getPassword(), user.getId()});
		} else {
			// insert
			String sql = "INSERT INTO users (login, password)"
						+ " VALUES (?, ?)";
			jdbcTemplate.update(sql, user.getLogin(), user.getPassword());
			
		}
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
				String sql = "SELECT * FROM users WHERE id=" + userId;
				
				return jdbcTemplate.query(sql, new ResultSetExtractor<User>(){
					@Override
					public User extractData(ResultSet rs) throws SQLException,
						DataAccessException {
						if(rs.next()){
							User user = new User();
							user.setId(rs.getInt("id"));
							user.setLoginId(rs.getString("login"));
							user.setPassword(rs.getString("password"));
							return user;
						}
						return null;
					}
				});
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users";
		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>(){
			@Override 
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setLoginId(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				
				return user;
			}
		});
		return listUser;
	}
	
}
