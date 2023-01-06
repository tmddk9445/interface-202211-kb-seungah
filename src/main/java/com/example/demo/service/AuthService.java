package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.AuthPostDto;
import com.example.demo.dto.response.ResponseDto;

// @Service : 해당 클래스가 Service 레이어 역할을 한다. 
@Service
public class AuthService {
	public ResponseDto<LoginDto> login(AuthPostDto dto) {
		LoginDto result = new LoginDto("hello", 3600000);
		return ResponseDto.setSuccess("", result); // 인스턴스 메서드
				
	}
}
