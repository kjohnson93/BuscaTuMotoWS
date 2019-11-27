package com.buscatumoto.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class TestSpringRestService {


	@GetMapping("/hello")
	fun helloString(): String {

		return "hello from my first web service"
	}

}