package com.buscatumoto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication
class BuscaTuMotoWsApplication

fun main(args: Array<String>) {
	runApplication<BuscaTuMotoWsApplication>(*args)
}
