package com.buscatumoto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BuscaTuMotoWsApplication

fun main(args: Array<String>) {
	runApplication<BuscaTuMotoWsApplication>(*args)
}
