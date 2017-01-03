package com.nvault.message.service;

import com.nvault.message.model.PasswordDetails;

public interface PwdDtlsService {
	PasswordDetails getPwdDtls(String uniqueId);

	PasswordDetails savePwdDtls(PasswordDetails pwdDtls);

}
