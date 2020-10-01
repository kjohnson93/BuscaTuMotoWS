package com.buscatumoto.services

import org.springframework.data.mongodb.repository.MongoRepository
import com.buscatumoto.models.Moto

interface MotoDAO: MongoRepository<Moto, String> {
	//tries to find motoBrand
//	fun findByBrandId(id:String): List<Moto>
	
	//not supported by mongodb. only especial and reserved keywords!
	fun findByBrand(brand:String): List<Moto>
	
		//tries to find motoBrand
//		fun findByMotoBrand(brand:String): List<Moto>


}