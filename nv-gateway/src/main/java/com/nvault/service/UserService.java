package com.nvault.service;


import com.nvault.model.NVaultUser;

public interface UserService {

	NVaultUser findByUserName(String userName);

	NVaultUser findById(int id);

	NVaultUser findByName(String name);

	NVaultUser saveUser(NVaultUser user);

	NVaultUser updateUser(NVaultUser user);
	
	NVaultUser findByEmailID(String mail,String userName);

	NVaultUser updatePassword(String password,String email,String userName) throws Exception;
	
	

}
