package com.mahar.contact.dao;

import java.util.List;

import com.mahar.contact.model.User;

public interface UserDAO {
	public void saveOrUpdate(User user);
	public void delete(int userId);
	public User getUser(int userId);
	public List<User> list();
}
