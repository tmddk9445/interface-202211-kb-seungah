package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.user.GetUserResponseDto;
import com.example.demo.dto.user.PatchUserDto;
import com.example.demo.dto.user.PostUserDto;
import com.example.demo.dto.user.ResultResponseDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired UserService userService;
	
	// CRUD
	
	// R
	@PostMapping("")
	public ResponseDto<ResultResponseDto> postUser(@RequestBody PostUserDto requestBody) {
		return userService.postUser(requestBody);
	}
	
	@GetMapping("")
	public ResponseDto<List<GetUserResponseDto>> getAllUser(){
		return userService.getAllUser();
	}
	
	// C
	@GetMapping("{email}")
	public ResponseDto<GetUserResponseDto> GetUser(@PathVariable("email") String email) { // 어노테이션 pathVariable("path")  .
		return userService.GetUser(email);
	}
	//Dto를 새로 class 만들어서 넣어야한다.
	
	// U
	@PatchMapping("")
	public ResponseDto<GetUserResponseDto> patchUser(@RequestBody PatchUserDto requestBody) {
		return userService.patchUser(requestBody);	
	}
	
	// D
	@DeleteMapping("{email}") // path형태로 받아온다. //쿼리 X
	public ResponseDto<ResultResponseDto> deleteUser(@PathVariable("email") String email){
		return userService.deleteUser(email);
	}
	
}










