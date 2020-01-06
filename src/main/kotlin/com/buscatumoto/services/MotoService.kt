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
import java.lang.NumberFormatException

import org.apache.logging.log4j.Logger
import com.sun.enterprise.module.common_impl.LogHelper
import java.util.logging.Level
import com.buscatumoto.models.Brand
import com.mongodb.BasicDBObject
import com.mongodb.client.model.Sorts
import java.util.Calendar
import java.util.ArrayList
import com.buscatumoto.models.MotoField
import com.buscatumoto.model.MotoResponse
import com.buscatumoto.model.MotoFieldResponse


@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class MotoService(val branDAO: BrandDAO, val motoDAO: MotoDAO, val mongoTemplate: MongoTemplate) :
	BasicCrud<String, Moto> {

	/*
 	## SEARCH METHODS
	 */
	
	fun search(search: String): List<Moto> {

		var criteria: Criteria = Criteria.where("model").regex(search, "i")
		var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)

		return result
	}

	fun filter(
		brand: String?, model: String?,
		tipo: String?,
		precio_d: Int?, precio_u: Int?,
		power_d: Float?, power_u: Float?,
		cil_d: Float?, cil_u: Float?,
		weight_d: Float?, weight_u: Float?,
		year: Int?, license: String?
	): MotoResponse {

		var result = emptyList<Moto>()


		//Filtering
		var criteria = Criteria()
		var criteriaPriceD = Criteria()
		var criteriaPriceU = Criteria()
		var criteriaPowerD = Criteria()
		var criteriaPowerU = Criteria()
		var criteriaCilD = Criteria()
		var criteriaCilU = Criteria()
		var criteriaWeightD = Criteria()
		var criteriaWeightU = Criteria()


		brand?.let {
			criteria.and("brand").`is`(brand)
		}

		model?.let {
			criteria.and("model").`is`(model)
		}
		tipo?.let {
			criteria.and("bikeType").`is`(tipo)
		}

		precio_d?.let {
			criteriaPriceD = Criteria.where("price").gte(it)
		}

		precio_u?.let {
			criteriaPriceU = Criteria.where("price").lte(it).gte(0)
		}

		power_d?.let {
			criteriaPowerD = Criteria.where("power").gte(it)
		}
		power_u?.let {
			criteriaPowerU = Criteria.where("power").lte(it).gte(0)
		}

		cil_d?.let {
			criteriaCilD = Criteria.where("displacement").gte(it)
		}
		cil_u?.let {
			criteriaCilU = Criteria.where("displacement").lte(it).gte(0)
		}

		weight_d?.let {
			criteriaWeightD = Criteria.where("weight").gte(it)
		}
		weight_u?.let {
			criteriaWeightU = Criteria.where("weight").lte(it).gte(0)
		}

		criteria.andOperator(
			criteriaPriceD, criteriaPriceU,
			criteriaPowerD, criteriaPowerU,
			criteriaCilD, criteriaCilU,
			criteriaWeightD, criteriaWeightU
		)

		year?.let {
			criteria.and("year").`is`(year)
		}
		
		license?.let {
		
			criteria.and("licenses").`in`(license)
		}

		var query = Query(criteria)

		result = mongoTemplate.find(query, Moto::class.java)
		
		
		var response = MotoResponse("No se ha encontrado ningún resultado con el criterio utilizado"
			, emptyList())
		
		result?.let {
			if (result.size > 0) {
				response = MotoResponse("OK", result)
			} else {
				response = MotoResponse("KO", result)
			}
		} 

		return response
	}
	
	//get bikes by brand
	fun getByBrand(brand: String): List<Moto> {
		var criteria: Criteria = Criteria.where("brand").`is`(brand)
		var query = Query(criteria)
		val result = mongoTemplate.find(query, Moto::class.java)

		return result
	}

	
	/*
 	## SEARCH METHODS
	 */
	
	
	/*
 	## FILTER FIELDS METHODS
  	*/
	
	//get all fields
	fun getFilterFormFields(): MotoFieldResponse {
		var result = MotoFieldResponse()
		
		val brands = getBrands()
		val bikeTypes = getBikeTypes()
		
		var priceMin = getPriceMin()
		var priceMax = getPriceMax()
		var powerMin = getPowerMin()
		var powerMax = getPowerMax()
		var cilMin = getCilMin()
		var cilMax = getCilMax()
		var weightMin = getWeightMin()
		var weightMax = getWeightMax()
		var years = getYears()
		var licenses = getLicenses()
		
		result.respuesta = "OK"
		result.brandList = brands
		result.bikeTypesList = bikeTypes
		result.priceMinList = priceMin
		result.priceMaxList = priceMax
		result.powerMinList = powerMin
		result.powerMaxList = powerMax
		result.cilMinList = cilMin
		result.cilMaxList = cilMax
		result.weightMinList = weightMin
		result.weightMaxList = weightMax
		result.yearList = years
		result.licenses = licenses

		return result
	}
	
	//get brands
	fun getBrands(): List<String> {
		val result = mongoTemplate.findDistinct("brand", Moto::class.java, String::class.java)
		result.sort()
		
		return result
	}
	
	//get bikeTypes
	fun getBikeTypes(): List<String> {
		val result = mongoTemplate.findDistinct("bikeType", Moto::class.java, String::class.java)
		result.sort()
		
		return result
	}
	
	//get prices minumum spinner values
	fun getPriceMin(): List<Int> {
		return mongoTemplate.findDistinct("priceMin", MotoField::class.java, Int::class.java)
	}
	
	//get prices maximun spinner values
	fun getPriceMax(): List<Int> {
		return mongoTemplate.findDistinct("priceMax", MotoField::class.java, Int::class.java)
	}	
	
	//get power minimum spinner values
	fun getPowerMin(): List<Float> {
		return mongoTemplate.findDistinct("powerMin", MotoField::class.java, Float::class.java)
	}
	
	//get power maximun spinner values
	fun getPowerMax(): List<Float> {
		return mongoTemplate.findDistinct("powerMax", MotoField::class.java, Float::class.java)
	}
	
	//get displacement minimum spinner values
	fun getCilMin(): List<Float> {
		return mongoTemplate.findDistinct("cilMin", MotoField::class.java, Float::class.java)
	}
	
	//get displacement maximun spinner values
	fun getCilMax(): List<Float> {
		return mongoTemplate.findDistinct("cilMax", MotoField::class.java, Float::class.java)
	}
	//get weight minimum spinner values
	fun getWeightMin(): List<Float> {
		return mongoTemplate.findDistinct("weightMin", MotoField::class.java, Float::class.java)
	}
	
	//get weight maximun spinner values
	fun getWeightMax(): List<Float> {
		return mongoTemplate.findDistinct("weightMax", MotoField::class.java, Float::class.java)
	}
	
	//get year spinner values. Gets all years in db, except for years greaters than current one.
	fun getYears(): List<Int> {
		val result = mongoTemplate.findDistinct("year", Moto::class.java, Int::class.java)
		result.sort()
		
		val currentYear = Calendar.getInstance().get(Calendar.YEAR)
		
		val yearsToRemove = ArrayList<Int>()
		
		result.forEach {
			if (it > currentYear) {
				yearsToRemove.add(it)
			}
		}
		
		result.removeAll(yearsToRemove)
		
		if (result.contains(-1)) {
			result.remove(-1)
		}
		
		result.sortDescending()
				
		return result
	}
	
	//licenses
	fun getLicenses(): List<String> {
		
		val result = mongoTemplate.findDistinct("licenses", MotoField::class.java, String::class.java)
		
		val aux0 = result[0]
		result[0] = result[2]
		result[2] = aux0
		
		val aux1 = result[1]
		result[1] = result[3]
		result[3] = aux1
		
		return result
	}
	
	
	/*
 	## FILTER FIELDS METHODS
  	*/
		
	/*
 	## GET INFO METHODS
	 */
	
	//Moto Catalog info
	
	//Moto detail info
	
	/*
 	## GET INFO METHODS
	 */
	
	/*
 	## TEST METHODS
	 */
	
	override fun getAll(pageable: Pageable): Page<Moto> {
		return motoDAO.findAll(pageable)
	}



	fun getByBikeType(bikeType: String): List<Moto> {
		var criteria: Criteria = Criteria.where("bikeType").`is`(bikeType)
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
	
	/*
 	## TEST METHODS
	 */
}