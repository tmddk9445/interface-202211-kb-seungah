package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.user.GetUserResponseDto;
import com.example.demo.dto.user.PostUserDto;
import com.example.demo.dto.user.PostUserResponseDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

@Service
public class UserService {
	
	@Autowired MemberRepository memberRepository;
	
	public ResponseDto<GetUserResponseDto> GetUser(String email){ // email은 Controller에서 이미 검증됨.
		// 해당 이메일을 데이터베이스에서 검색,
		
		MemberEntity member;
		try {
			member = memberRepository.findById(email).get();
		// 존재하지 않으면 "Not Exist User" 메시지를 포함한 Failed Response 반환
		} catch (Exception e) {
			return ResponseDto.setFailed("Not exist User");
		}
		
		// 존재하지 않는다면 : 없는 아이디 "로그인 실패" 반환
		
		// 존재하면 User 정보를 꺼내서 반환한다.
//		GetUserResponseDto responseData = new GetUserResponseDto();
//		responseData.setEmail(member.getEmail());
//		responseData.setNickname(member.getNickname());
//		responseData.setProfile(member.getProfile());
//		responseData.setTelNumber(member.getTelNumber());
//		responseData.setAddress(member.getAddress());
		
//		return ResponseDto.setSuccess("Get User Success", responseData);
		
		// 2. 빌더 추가
//		GetUserResponseDto responseData =
//				GetUserResponseDto
//				.builder()
//				.email(member.getEmail())
//				.nickname(member.getNickname())
//				.profile(member.getProfile())
//				.telNumber(member.getTelNumber())
//				.address(member.getAddress())
//				.build();
				
		// 3. 
//		return ResponseDto.setSuccess("Get User Success", new GetUserResponseDto(
//				new GetUserResponseDto(
//						member.getEmail(),
//						member.getNickname(),
//						member.getProfile(),
//						member.getTelNumber(),
//						member.getAddress()
//						)
//				);
		return ResponseDto.setSuccess("Get User Success", new GetUserResponseDto(member));

	}
	
	public ResponseDto<PostUserResponseDto> postUser(PostUserDto dto){
		
		// 데이터베이스에 해당 email이 존재하는지 체크
		// 존재한다면 Failed Response를 반환
		String email = dto.getEmail();
		
		try {
			if(memberRepository.existsById(email))
				return ResponseDto.setFailed("이미 존재하는 이메일입니다.");
			// Repository를 쓰는 것은 try catch안에 담아주는 것이 좋다. 
			// 위의 if문도 try catch안에 넣어주는 것이 좋다.
		} catch (Exception e) {
			return ResponseDto.setFailed("데이터베이스 오류입니다.");
		}
		
//		try {
//			MemberEntity member = memberRepository.findById(email).get();
//			 MemberEntity member = 생략 가능
//		} catch (Exception e) {
//			return ResponseDto.setFailed("이미 존재하는 이메일입니다.");
//		}
		
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
		
		
		// JpaRepository.save(Entity) 메서드
		// 해당 Entity Id가 데이터베이스 테이블에 존재하지 않으면 >> Entity를 INSERT하는 작업을 수행
		
		// 하지만 해당 Entity Id가 데이터베이스 테이블에 존재하면 >> 존재하는 Entity를 UPDATE하는 작업을 수행 
		
		// Insert작업과 Update작업을 분리하는 것이 좋다.
		
		memberRepository.save(member);
		
		return ResponseDto.setSuccess("회원가입에 성공했습니다.", new PostUserResponseDto(true));
	}
}
