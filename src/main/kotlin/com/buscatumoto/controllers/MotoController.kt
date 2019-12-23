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

@RestController
@RequestMapping("api/moto")
class MotoController(val motoService: MotoService) {
	
	@GetMapping
	fun getAll(pageable: Pageable): Page<Moto> {
		return motoService.getAll(pageable)
	}
	
	@GetMapping("/brand/{brand}")
	fun getByBrand(@PathVariable brand: String): List<Moto> {
		return motoService.getByBrand(brand)
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
	
	@GetMapping("/filter")
	fun filter(@RequestParam("brand") brand: String, @RequestParam("model") model: String,
			   @RequestParam("tipo") tipo: String, @RequestParam("precio") precio: String,
			   @RequestParam("precio_d") precio_d: String, @RequestParam("precio_u") precio_u: String,
			   @RequestParam("cil_d") cil_d: String, @RequestParam("cil_u") cil_u: String,
			   @RequestParam("year") year: String, @RequestParam("a2") a2: String): List<Moto> {
		return motoService.filter(brand, model, tipo, precio, precio_d, precio_u, cil_d, cil_u, year, a2)
	}
	
	
	
	
	@PostMapping
	fun insert(@RequestBody moto: Moto): Moto {
		return motoService.insert(moto)
	}


	
	@DeleteMapping("{id}")
	fun delete(@PathVariable id: String): Optional<Moto> {
		return motoService.deleteById(id)
	}
}