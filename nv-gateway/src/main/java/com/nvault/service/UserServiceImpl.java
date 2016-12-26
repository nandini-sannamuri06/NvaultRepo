package com.nvault.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nvault.model.NVaultUser;
import com.nvault.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public NVaultUser findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	@Override
	public NVaultUser findById(int id) {
		
		return userRepository.findOne(id);
	}

	@Override
	public NVaultUser findByName(String name) {
		
		return null;
	}

	@Override
	public NVaultUser saveUser(NVaultUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		NVaultUser saveduser = userRepository.save(user);
		
		return saveduser;
		
	}

	@Override
	public NVaultUser updateUser(NVaultUser user) {
		
		return null;
	}

	@Override
	public void deleteUserById(long id) {
		
		
	}

	@Override
	public List<NVaultUser> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

}
