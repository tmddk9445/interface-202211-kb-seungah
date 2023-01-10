package com.example.demo.dto.user;

import com.example.demo.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 생성자, get set메서드, toString...
@Builder
public class GetUserResponseDto {
	private String email;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;

	public GetUserResponseDto(MemberEntity member) {
		this.email = member.getEmail();
		this.nickname = member.getNickname();
		this.profile = member.getProfile();
		this.telNumber = member.getTelNumber();
		this.address = member.getAddress();
	}
}
