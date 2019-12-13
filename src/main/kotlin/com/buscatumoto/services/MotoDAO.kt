package com.buscatumoto.services

import org.springframework.data.mongodb.repository.MongoRepository
import com.buscatumoto.models.Moto

interface MotoDAO: MongoRepository<Moto, String> {
//	fun findByBrandId(id:String): List<Moto>
	fun findByBrand(brand:String): List<Moto>
}