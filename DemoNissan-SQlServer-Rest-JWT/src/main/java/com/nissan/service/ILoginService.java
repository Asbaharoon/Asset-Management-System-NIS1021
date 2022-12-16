package com.nissan.service;

import com.nissan.common.APIResponse;
import com.nissan.dto.LoginRequestDTO;
import com.nissan.dto.SignUpRequestDTO;

public interface ILoginService {

	//SignUp
	APIResponse signUp(SignUpRequestDTO SignUpRequestDTO);
	
	//Login
	APIResponse login(LoginRequestDTO login);
}
