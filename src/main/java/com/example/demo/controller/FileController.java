package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

@RestController
@RequestMapping("file")
public class FileController {
	
	@Autowired FileService fileService;
	
	// 파일을 서버에 업로드 - post
	@PostMapping("upload")
	// @RequestParam(field명) : RequestBody에서 특정 필드를 받아온다. // body는 전체
	// Request body에 파일을 받아 올 땐 MultipartFile 인스턴스로 받아온다.
	public String fileUpload(@RequestParam("file") MultipartFile file)  {
		return fileService.fileUpload(file);
	}
	
//	// 파일을 서버에서 다운로드 - get
	@GetMapping("download/{fileName}") // url에서 값을 받는다.
	public ResponseEntity<Resource> fileDownload(@PathVariable("fileName") String fileName) {
		// ResponseEntity : Response로 가는 데이터를 변경할 수 있다.
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + fileName)
				.body(fileService.fileDownload(fileName));
	}
	
//	// 이미지 파일 일 경우 이미지를 출력 - get
	@GetMapping(value = "image/{imageName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public Resource getImage(@PathVariable("imageName") String imageName) {
		return fileService.getImage(imageName);
	} 
}
