package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HelloDto;
import com.example.demo.dto.response.ResponseDto;

// Rest : 메서드에 따라서 기능을 지정하는 것 // 현재는 GET메서드
// html을 반환하는 것이 아니다. // Controller : html을 반환한다.

// Response로 HTML을 반환하는 Controller가 아닌, Response Body에 직접 데이터를 담아서 응답하는 Controller
// @Controller + @ResposeBody
@RestController
// @RequestMapping(pattern) : http://localhost:4020/(end-point)/
// end-point의 패턴을 지정하여 해당 패턴의 end-point 일 때 해당 Controller를 실행
@RequestMapping("apis/")
public class MainController {
	static final String HELLO = "hello"; // static final을 사용해서 생성하지 않고도 value값을 사용하도록 지정하였다. 
	// http방식 ://g호스트:포트/~~~ (/~~~ : 'end-point' 라고 부름.)
	// http://localhostL:4040/~~~ : 
	
	// GetMappting(end-point) : 해당 end-point로 Get 방식의 Request가 왔을 때 동작한다.
	@GetMapping("")
	public String hello() {
		return "Hello Spring Boot World!";
	}
	// @RequestParam(name="", value="", required=true, defaultValue="")
	//  : Url로 데이터를 받는 경우 (Get, Delete) 쿼리 형태로 데이터를 받는다.
	// http://호스트:포트/end-point?name=value&...

	@GetMapping(HELLO) // value 값을 가지고 왔다.
	public String getHello(@RequestParam(name="name", required=false, defaultValue="james") String name) {
		return "This is get method, end-point '/hello'"  + name;
	}
	
	@GetMapping(HELLO + "/{name}/spring") // 중괄호 안에 있는 값을 가지고 온다.
	// @PathVariable(path) : URL 데이터를 받는 경우 (Get, Delete) path 형태로 데이터를 받는다.
	// http://호스트:포트/end-point/VARIABLE
	public String getHelloName(@PathVariable("name") String name) {
		return "This is get method, end-point '/hello'" + name;
	}
	
	// @PostMapping(end-point) : 해당 end-point로 Post 방식의 Request가 왔을 때 동작
	@PostMapping(HELLO)
	// @RequestBody : 해당 Request의 Body에서 Json을 인식해 인스턴스로 변경
	public ResponseDto<HelloDto> postHello(@RequestBody HelloDto requestBody) {
		
		return ResponseDto.setSuccess("hello", requestBody);
	}
	
	// @PutMapping : 해당 end-point로 Put 방식의 Request가 왔을 때 동작
	@PutMapping(HELLO)
	public String putHello() {
		return "This is put method, end-point '/hello'";
	}
	
	// @PatchMapping(end-point) : 해당 end-point로 Patch 방식의 Request가 왔을 때 동작
	@PatchMapping(HELLO)
	public String patchHello() {
		return "This is patch method, end-point '/hello'";
	}
	
	// @DeleteMapping(end-point) : 해당 end-point로 Delete 방식의 Request가 왔을 때 동작
	@DeleteMapping(HELLO)
	public String deleteHello() {
		return "This is delete method, end-point '/hello'";
	}
		
}
