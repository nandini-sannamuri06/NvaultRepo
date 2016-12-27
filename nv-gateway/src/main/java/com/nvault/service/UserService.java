package com.nvault.service;

import java.util.List;

import com.nvault.model.NVaultUser;

public interface UserService {

	NVaultUser findByUserName(String userName);

	NVaultUser findById(int id);

	NVaultUser findByName(String name);

	NVaultUser saveUser(NVaultUser user);

	NVaultUser updateUser(NVaultUser user);

	void deleteUserById(long id);

	List<NVaultUser> findAllUsers();

	void deleteAllUsers();

}
