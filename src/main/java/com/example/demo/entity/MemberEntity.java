package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
Member
이메일
비밀번호
닉네임
프로필 사진
전화번호 
주소
*/

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="MEMBER")
// 해당 Entity 클래스와 데이터베이스 Table을
// 인자로 지정한 이름으로 매핑
@Table(name="MEMBER")
public class MemberEntity { // DB와 연결하는 repository
	// 해당 필드가 Primary Key임을 명시
	@Id
	private String email;
	// 해당 Primary key의 value 자동 생성을 지시
	// @GeneratedValue
	private String password;
	private String nickname;
	private String profile;
	private String telNumber;
	private String address;
}
