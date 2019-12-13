package com.buscatumoto.controllers

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.buscatumoto.services.BrandService
import com.buscatumoto.models.Brand
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.Optional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("api/brand")
class BrandController(val brandService: BrandService) {
	
	//http requests
	//getAllBrands
	@GetMapping
	fun getAll(pageable: Pageable) : Page<Brand> {
	 	return brandService.getAll(pageable)
	}
	//getBrandById
	@GetMapping("{id}")
	fun getBrandById(@PathVariable id: String): Optional<Brand> {
		return brandService.getById(id)
	}
	//postBrand
	@PostMapping
	fun insert(@RequestBody brand: Brand) {
		brandService.insert(brand)
	}
	//putBrand
	@PutMapping
	fun update(@RequestBody brand: Brand) {
		brandService.update(brand)
	}
	
	//deleteBrand
	@DeleteMapping("{id}")
	fun delete(@PathVariable id: String): Optional<Brand> {
		return brandService.deleteById(id)
	}
}