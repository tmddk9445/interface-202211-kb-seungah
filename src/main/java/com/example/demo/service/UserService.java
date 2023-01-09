package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.user.PostUserDto;
import com.example.demo.dto.user.PostUserResponseDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

@Service
public class UserService {
	
	@Autowired MemberRepository memberRepository;
	
	public ResponseDto<PostUserResponseDto> postUser(PostUserDto dto){
		String password = dto.getPassword();
		String password2 = dto.getPassword2();
		
		if (!password.equals(password2)) return ResponseDto.setFailed("비밀번호가 서로 다릅니다.");
		
		MemberEntity member = MemberEntity
				.builder()
				.email(dto.getEmail())
				.password(dto.getPassword())
				.nickname(dto.getNickname())
				.telNumber(dto.getTelNumber())
				.address(dto.getAddress() + " " + dto.getAddressDetail())
				.build();
		
		
		memberRepository.save(member);
		
		return ResponseDto.setSuccess("회원가입에 성공했습니다.", new PostUserResponseDto(true));
	}
}
