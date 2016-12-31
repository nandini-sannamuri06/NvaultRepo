package com.nvault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.model.PasswordDetails;
import com.nvault.repository.PasswordDtlsRepository;

@Service
public class PwdDtlsServiceImpl implements PwdDtlsService {
	@Autowired
	public PasswordDtlsRepository passwodDtlsRepo;
	
	@Override
	public PasswordDetails getPwdDtls(String uniqueId) {
		return passwodDtlsRepo.findByUniqueId(uniqueId);
	}

	@Override
	public PasswordDetails savePwdDtls(PasswordDetails pwdDtls) {
		return passwodDtlsRepo.save(pwdDtls);
	}
}
