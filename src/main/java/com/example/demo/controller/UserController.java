package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.user.GetUserResponseDto;
import com.example.demo.dto.user.PostUserDto;
import com.example.demo.dto.user.PostUserResponseDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping("{email}")
	public ResponseDto<GetUserResponseDto> GetUser(@PathVariable("email") String email) { // 어노테이션 pathVariable("path")  
		return userService.GetUser(email);
	}
	//Dto를 새로 class 만들어서 넣어야한다.
	
	@PostMapping("")
	public ResponseDto<PostUserResponseDto> postUser(@RequestBody PostUserDto requestBody) {
		return userService.postUser(requestBody);
	}
}
