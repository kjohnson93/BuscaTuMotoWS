package com.buscatumoto.controllers

import com.buscatumoto.services.MotoService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import com.buscatumoto.models.Moto
import org.springframework.web.bind.annotation.PathVariable
import java.util.Optional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log

import com.buscatumoto.models.Brand


@RestController
@RequestMapping("api/moto")
class MotoController(val motoService: MotoService) {
	
	/*
 	## SEARCH METHODS
	 */
		@GetMapping("/filter")
	fun filter(@RequestParam(name = "brand", required = false) brand: String? = "",
			   @RequestParam(name = "model", required = false) model: String? = "",
			   @RequestParam(name = "tipo", required = false) tipo: String? = "",
			   @RequestParam(name = "precio_d", required = false) precio_d: Int? = 0,
			   @RequestParam(name = "precio_u", required = false) precio_u: Int? = 0,
   		       @RequestParam(name = "power_d", required = false) power_d: Float? = 0f,
		       @RequestParam(name = "power_u", required = false) power_u: Float? = 0f,
			   @RequestParam(name = "cil_d", required = false) cil_d: Float? = 0f,
			   @RequestParam(name = "cil_u", required = false) cil_u: Float? = 0f,
	           @RequestParam(name = "weight_d", required = false) weight_d: Float? = 0f,
		       @RequestParam(name = "weight_u", required = false) weight_u: Float? = 0f,
			   @RequestParam(name = "year", required = false) year: Int? = 0,
		       @RequestParam(name = "license", required = false) a2: String? = ""
	): List<Moto> {
		return motoService.filter(brand, model, tipo, precio_d, precio_u,
			power_d, power_u, cil_d, cil_u, weight_d, weight_u, year, a2)
	}
	
	@GetMapping("/search/{search}")
	fun search(@PathVariable search: String): List<Moto> {
		return motoService.search(search)
	}
	/*
 	## SEARCH METHODS
	 */
	
	/*
 	## FILTER FIELDS METHODS
  	*/
	
	//get brands
	@GetMapping("/field/brands")
	fun getBrands(): List<String> {
		return motoService.getBrands()
	}
	
	
	//get bikes by brand
	@GetMapping("/brand/{brand}")
	fun getByBrand(@PathVariable brand: String): List<Moto> {
		return motoService.getByBrand(brand)
	}
		
	//get bikeTypes
	@GetMapping("/field/biketypes")
	fun getBikeTypes(): List<String> {
		return motoService.getBikeTypes()
	}
	
	//get prices minumum spinner values
	
	//get prices maximun spinner values
		
	//get power minimum spinner values
	
	//get power maximun spinner values
	
	//get displacement minimum spinner values
	
	//get displacement maximun spinner values
	
	//get weight minimum spinner values
	
	//get weight maximun spinner values
	
	//get year spinner values
	@GetMapping("/field/years")
	fun getYears(): List<Int> {
		return motoService.getYears()
	}
	
	@GetMapping("/field/priceMin")
	fun getPriceMin(): List<Int> {
		return motoService.getPriceMin()
	}
	
	@GetMapping("/field/priceMax")
	fun getPriceMax(): List<Int> {
		return motoService.getPriceMax()
	}
	
	@GetMapping("/field/powerMin")
	fun getPowerMin(): List<Float> {
		return motoService.getPowerMin()
	}
	
	@GetMapping("/field/powerMax")
	fun getPowerMax(): List<Float> {
		return motoService.getPowerMax()
	}
	
	@GetMapping("/field/cilMin")
	fun getCilMin(): List<Float> {
		return motoService.getCilMin()
	}
	
	@GetMapping("/field/cilMax")
	fun getCilMax(): List<Float> {
		return motoService.getCilMax()
	}
	
	@GetMapping("/field/weightMin")
	fun getWeightMin(): List<Float> {
		return motoService.getWeightMin()
	}
	
	@GetMapping("/field/weightMax")
	fun getWeightMax(): List<Float> {
		return motoService.getWeightMax()
	}
			
	@GetMapping("/field/licenses")
	fun getLicenses(): List<String> {
		return motoService.getLicenses()
	}

	
	/*
 	## VIEW FIELDS METHODS
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
	
	@GetMapping
	fun getAll(pageable: Pageable): Page<Moto> {
		return motoService.getAll(pageable)
	}
	

	
	@GetMapping("/bikeType/{bikeType}")
	fun getByBikeType(@PathVariable bikeType: String): List<Moto>{
		return motoService.getByBikeType(bikeType)
	}
	
	@GetMapping("{id}")
	fun getById(@PathVariable id: String): Optional<Moto> {
		return motoService.getById(id)
	}
	
	@GetMapping("/license/{id}")
	fun getLicenseTitle(@PathVariable id: String) : String {
		return motoService.getLicenseTitle(id)
	}

	@PostMapping
	fun insert(@RequestBody moto: Moto): Moto {
		return motoService.insert(moto)
	}

	@DeleteMapping("{id}")
	fun delete(@PathVariable id: String): Optional<Moto> {
		return motoService.deleteById(id)
	}
	
	/*
 	## TEST METHODS
	 */
}