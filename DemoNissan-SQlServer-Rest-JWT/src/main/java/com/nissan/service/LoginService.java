package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.dao.IUserRepository;
import com.nissan.dto.LoginRequestDTO;
import com.nissan.dto.SignUpRequestDTO;
import com.nissan.model.User;
import com.nissan.util.JwtUtils;

@Service
public class LoginService implements ILoginService {
	//
	@Autowired
	private IUserRepository userRepository;
//	@Autowired
//	User user;
	APIResponse apiResponse =new APIResponse();
	@Autowired
	private JwtUtils jwtUtils;
	@Override
	public APIResponse signUp(SignUpRequestDTO SignUpRequestDTO) {
		//DTO to Entity ----SignUpRequestDTO to User
		User user= new User();
		user.setUsername(SignUpRequestDTO.getUserName());
		user.setEmailId(SignUpRequestDTO.getEmailId());
		user.setPassword(SignUpRequestDTO.getPassword());
		user.setRoleId(SignUpRequestDTO.getRoleId());

		//Save to Entity ---ORM --Database
		userRepository.save(user);
		
		//genrate token
		String token = jwtUtils.generateJwt(user);
		
		//Store more details
		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		data.put("Role", user.getRole());
		data.put("emailId", user.getEmailId());
		
		apiResponse.setData(data);
		return apiResponse;
	}
	@Override
	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		User user=userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),loginRequestDTO.getPassword());  // avoided either use Autowired --- anonymous

        if(user==null) {
            apiResponse.setData("User login failed");
            return apiResponse;
        }
        	//genrate token
      		String token = jwtUtils.generateJwt(user);
      		
      		//Store more details
      		Map<String, Object> data = new HashMap<>();
      		data.put("accessToken", token);
      		data.put("Role", user.getRole());
      		data.put("emailId", user.getEmailId());
      		
      		apiResponse.setData(data);
      		return apiResponse;
	}

	

}
