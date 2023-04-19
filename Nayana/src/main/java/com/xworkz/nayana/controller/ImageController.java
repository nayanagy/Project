package com.xworkz.nayana.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class ImageController {

	public ImageController() {
		System.out.println("Created "+this.getClass().getSimpleName());
	}
	
	@PostMapping("upload")
	public String onUpload(@RequestParam("chitra") MultipartFile multipartFile) throws IOException{
		System.out.println("multipartFile"+multipartFile);
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getContentType());
		System.out.println(multipartFile.getSize());
		System.out.println(multipartFile.getBytes());
		
		byte[] bytes=multipartFile.getBytes();
		Path path=Paths.get("D:\\Jewellery-files\\"+multipartFile.getOriginalFilename());
		Files.write(path, bytes);
		return "ImageUpload";
		
	}
}
