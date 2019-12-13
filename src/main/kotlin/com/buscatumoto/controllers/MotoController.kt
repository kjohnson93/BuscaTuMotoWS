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

@RestController
@RequestMapping("api/moto")
class MotoController(val motoService: MotoService) {
	
	@GetMapping
	fun getAll(pageable: Pageable): Page<Moto> {
		return motoService.getAll(pageable)
	}
	
	@GetMapping("{id}")
	fun getById(@PathVariable id: String): Optional<Moto> {
		return motoService.getById(id)
	}
	
	@PostMapping
	fun insert(@RequestBody moto: Moto): Moto {
		return motoService.insert(moto)
	}

	@PutMapping	
	fun update(@RequestBody moto: Moto): Moto {
		return motoService.update(moto)
	}
	
	@DeleteMapping("{id}")
	fun delete(@PathVariable id: String): Optional<Moto> {
		return motoService.deleteById(id)
	}
}