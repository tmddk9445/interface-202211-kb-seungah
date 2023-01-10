package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.auth.AuthPostDto;
import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

// @Service : 해당 클래스가 Service 레이어 역할을 한다. 
@Service
public class AuthService {
	
	@Autowired MemberRepository memberRepository;
	
	public String hello() {
		// Entity Class로 entity 빌드
		MemberEntity memberEntity = 
				MemberEntity
				.builder()
				.email("qwe@123.com")
				.password("qwe123")
				.nickname("hi")
				.telNumber("010-1234-5678")
				.address("busan")
				.build();
		// 빌드한 Entity를 데이터베이스에 저장
		memberRepository.save(memberEntity);
		
		// MemberRepository가 상속받은 JpaRepository 메서드를 사용하여 데이터 검색
		MemberEntity savedMemberEntity = 
				memberRepository.findById("qwe@123.com").get(); 
		
		// MemberRepository에 작성한 커스텀 메서드를 사용
		List<MemberEntity> list = memberRepository.myFindAll("qwe123@qwe.com");
		System.out.println(list.toString());
		
		return savedMemberEntity.getNickname();
	}
	
	public ResponseDto<LoginDto> login(AuthPostDto dto) {
		
		// 입력받은 email로 데이터베이스에서 검색
		String email = dto.getEmail();
		MemberEntity member; // try 밖에 선언해주어야 한다.
		
		// 존재하지 않는다면 : 없는 아이디 "로그인 실패" 반환
		
		try {
			member = memberRepository.findById(email).get(); 
		} catch (Exception e) {
			return ResponseDto.setFailed("Login Failed");
		}
		
		// 존재한다면 입력받은 패스워드와 데이터베이스의 패스워드와 동일한지 검사
		// 동일하지 않다면 "로그인 실패" 반환
		String password = dto.getPassword();
		String dbpassword = member.getPassword();
		
		if (!password.equals(dbpassword))
			return ResponseDto.setFailed("Login Failed"); // if문 중괄호 생략 가능
		
		// 토큰 생성 후 토큰 및 만료시간 반환
		
		LoginDto result = new LoginDto("hello", 3600000);
		return ResponseDto.setSuccess("", result); // 인스턴스 메서드
				
	}
}








