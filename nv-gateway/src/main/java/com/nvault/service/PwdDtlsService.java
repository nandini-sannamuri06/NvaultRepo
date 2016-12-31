package com.nvault.service;

import com.nvault.model.PasswordDetails;

public interface PwdDtlsService {
	PasswordDetails getPwdDtls(String uniqueId);

	PasswordDetails savePwdDtls(PasswordDetails pwdDtls);

}
