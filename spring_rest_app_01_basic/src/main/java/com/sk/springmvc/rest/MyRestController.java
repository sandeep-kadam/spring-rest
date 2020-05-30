package com.sk.springmvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myservice")
public class MyRestController {

	
	@GetMapping("/hello")
	public String getMessage() {
		return "Hello World";
	}
}
