package com.buscatumoto.services

import com.buscatumoto.util.BasicCrud
import com.buscatumoto.models.Moto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import java.util.Optional
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException
import org.springframework.stereotype.Service
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class MotoService(val branDAO: BrandDAO, val motoDAO: MotoDAO, val mongoTemplate: MongoTemplate) : BasicCrud<String, Moto> {


	override fun getAll(pageable: Pageable): Page<Moto> {
		return motoDAO.findAll(pageable)
	}
	
	fun getByBrand(brand: String): List<Moto> {
		var criteria: Criteria = Criteria.where("brand").`is`(brand)
		var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)
		
		return result
	}
	
	fun getByBikeType(bikeType: String): List<Moto> {
		var criteria: Criteria = Criteria.where("bikeType").`is`(bikeType)
		var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)
		
		return result
	}
	
	fun search(search: String): List<Moto> {

		var criteria: Criteria = Criteria.where("model").regex(search, "i")
				var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)
		
		return result
	}
	
	fun filter(brand: String?, model: String?,
			    tipo: String?, precio: String?,
			    precio_d: String?, precio_u: String?,
			    cil_d: String?, cil_u: String?,
			    year: String?, a2: String?): List<Moto> {
		
		//Validation
		
		var criteria: Criteria = Criteria.where("brand").`is`(brand)
		var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)
		
		return result
		
	}
	
	//test
	fun getLicenseTitle(id: String): String {
		
		var criteria: Criteria = Criteria.where("id").`is`(id)
		var query = Query(criteria)
		val result = mongoTemplate.findOne(query, Moto::class.java)
		
		result?.let {
			return it.licensesTitle
		} ?: run {
			return ""
		}

		
	}
	
//	fun getLicenseTitle(id: String): String {
//		
//		return motoDAO.findById(id).get().licensesTitle
//		
//	}

	override fun getById(id: String): Optional<Moto> {
		return motoDAO.findById(id)
	}


	override fun insert(obj: Moto): Moto {
		return motoDAO.insert(obj)
	}

//	override fun update(obj: Moto): Moto {
////		return motoDAO.
//		//upsert
////		motoDAO.findById(obj.id)?.let {
////			
////			
////			
////		} ?: run {
////			throw object: Exception("Trying to update a moto that does not exist.") {}
////		}
////		motoDAO.findById(obj.id).let {
////			
////			it.ifPresent {
////				return it
////			}
////			
////			
////		} ?: run {
////			throw object: Exception("Trying to update a moto that does not exist.") {}
////		}
////
//		if (motoDAO.existsById(obj.id)) {
//			//re insert brand from db to avoid inconsistency
//			return motoDAO.save(obj)
//		} else {
//			throw object : Exception("Trying to update a moto that does not exist.") {}
//
//		}
//
//	}

	override fun deleteById(id: String): Optional<Moto> {
//		motoDAO.findById(id)?.let {
//			
//		} ?: run {
//			return 
//		}
		return motoDAO.findById(id).apply {
			this.ifPresent() {
				motoDAO.delete(it)
			}
		}
	}
}