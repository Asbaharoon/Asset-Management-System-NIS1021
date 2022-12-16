package com.nissan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.dto.LoginRequestDTO;
import com.nissan.dto.SignUpRequestDTO;
import com.nissan.service.ILoginService;

@CrossOrigin
@RestController
@RequestMapping({"api/"})
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	//SignUp
	@PostMapping("signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	//Login
		@PostMapping("login")
		public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
			APIResponse apiResponse = loginService.login(loginRequestDTO);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
}
