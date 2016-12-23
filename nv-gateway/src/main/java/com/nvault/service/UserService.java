package com.nvault.service;

import java.util.List;

import com.nvault.model.User;

public interface UserService {

	User findByUserName(String userName);

	User findById(int id);

	User findByName(String name);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

}
