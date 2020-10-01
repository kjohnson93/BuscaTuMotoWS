package com.buscatumoto.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/rest")
class TestSpringRestService {
	
		
	@GetMapping("/hello")
	fun helloString(): String {
		
		return "hello from my first web service"
	}
	
}