package com.nvault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nvault.model.User;
import com.nvault.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	@Override
	public User findById(int id) {
		
		return userRepository.findOne(id);
	}

	@Override
	public User findByName(String name) {
		
		return null;
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User saveduser = userRepository.save(user);
		
		return saveduser;
		
	}

	@Override
	public User updateUser(User user) {
		
		return null;
	}

	@Override
	public void deleteUserById(long id) {
		
		
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

}
