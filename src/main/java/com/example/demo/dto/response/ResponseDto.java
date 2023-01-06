package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="set") // set 생성자 함수
public class ResponseDto<D> {
	private boolean status;
	private String message;
	private D data;
	
	public static <D> ResponseDto<D> setSuccess(String message, D data) {
	//	접근지정자(public-instance를 생성하지 않아도 클래스에 접근이 가능하다.)
	//	ResponseDto<D> 반환하는 자료형을 나타낸다.
	
	// ResponseDto는 <D>자료형을 제네릭으로 받고 있으나 public static 함수는 알지 못한다.
	//	함수에게 <D>는 제네릭 <D>이라고 미리 설명해둔다.
		return ResponseDto.set(true, message, data);
		// static이기 때문에 클래스에 .set으로 사용 가능하다.
		// 인스턴스에 값을 반환한다.
	}
	
	
	public static <D> ResponseDto<D> setFailed(String message) {
		return ResponseDto.set(false, message, null);
	}
}
