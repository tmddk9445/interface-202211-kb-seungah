package com.example.demo.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthPostDto {
	//필드에 해당하는 타입이 문자열이다.
	@NotNull
	@Email
	private String email;
	private String password;
	
}
