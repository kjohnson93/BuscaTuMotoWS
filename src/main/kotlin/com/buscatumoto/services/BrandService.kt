package com.buscatumoto.services

import com.z.bookbackend.util.BasicCrud
import com.buscatumoto.models.BrandDAO
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import java.util.Optional

class BrandService: BasicCrud<Int, BrandDAO> {
	
	override fun getAll(pageable: Pageable): Page<BrandDAO> {
		TODO()
	}

	override fun getById(id: Int): Optional<BrandDAO> {
		TODO()
	}

	override fun insert(obj: BrandDAO): BrandDAO {
		TODO()
	}

	override fun update(obj: BrandDAO): BrandDAO {
		TODO()
	}

	override fun deleteById(id: Int): Optional<BrandDAO> {
		TODO()
	}
}