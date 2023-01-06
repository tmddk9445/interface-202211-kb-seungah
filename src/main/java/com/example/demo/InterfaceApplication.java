package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 해당 어노테이션이 있는 클래스를 베이스 패키지로 간주
public class InterfaceApplication {  

	public static void main(String[] args) {
		SpringApplication.run(InterfaceApplication.class, args);
	}
}
